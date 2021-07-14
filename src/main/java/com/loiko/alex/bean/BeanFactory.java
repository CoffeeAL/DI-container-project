package com.loiko.alex.bean;

import com.loiko.alex.annotation.Inject;
import com.loiko.alex.configuration.Configuration;
import com.loiko.alex.configuration.PackageScanner;
import com.loiko.alex.configurator.BeanConfigurator;
import com.loiko.alex.configurator.BeanConfiguratorImpl;
import com.loiko.alex.context.ApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class BeanFactory {

    private final Configuration configuration;
    private final BeanConfigurator beanConfigurator;
    private ApplicationContext context;

    public BeanFactory(ApplicationContext context) {
        this.configuration = new PackageScanner();
        this.beanConfigurator = new BeanConfiguratorImpl(configuration.getPackageToScan(), configuration.interfaceToImplementations());
        this.context = context;
    }

    public <T> T getBean(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Class<? extends T> implementationClass = clazz;
        if (implementationClass.isInterface()) {
            implementationClass = beanConfigurator.getImplementationClass(clazz);
        }
        T bean = null;
        Constructor<?>[] declaredConstructors = implementationClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if (Modifier.isPrivate(declaredConstructor.getModifiers())) {
                declaredConstructor.setAccessible(true);
                Field[] fields = implementationClass.getFields();
                for (Field field : fields) {
                    if (field.getName() == implementationClass.getName()) {
                        field.setAccessible(true);
                        bean = (T) field.get(clazz);
                    }
                }
            } else {
                bean = implementationClass.getDeclaredConstructor().newInstance();
            }
        }
        List<Field> fields = Arrays.stream(implementationClass.getDeclaredFields()).filter(field ->
                field.isAnnotationPresent(Inject.class)).collect(Collectors.toList());
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(bean, context.getBean(field.getType()));
        }
        return bean;
    }

    public BeanConfigurator getBeanConfigurator() {
        return beanConfigurator;
    }
}