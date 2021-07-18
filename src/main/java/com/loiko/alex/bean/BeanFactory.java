package com.loiko.alex.bean;

import com.loiko.alex.annotation.Inject;
import com.loiko.alex.configuration.Configuration;
import com.loiko.alex.configuration.PackageScanner;
import com.loiko.alex.configurator.BeanConfigurator;
import com.loiko.alex.configurator.BeanConfiguratorImpl;
import com.loiko.alex.context.ApplicationContext;
import com.loiko.alex.exception.ConstructorNotFoundException;
import com.loiko.alex.exception.TooManyConstructorsException;
import com.loiko.alex.logger.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class BeanFactory {

    private final Configuration configuration;
    private final BeanConfigurator beanConfigurator;
    private ApplicationContext context;
    private static Logger logger = LoggerFactory.getLogger();

    public BeanFactory(ApplicationContext context) {
        configuration = new PackageScanner();
        beanConfigurator = new BeanConfiguratorImpl(configuration.getPackageToScan(), configuration.interfaceToImplementations());
        this.context = context;
    }

    public <T> T getBean(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<? extends T> implementationClass = clazz;
        if (implementationClass.isInterface()) {
            implementationClass = beanConfigurator.getImplementationClass(clazz);
        }
        int amountAnnotatedConstructors = countAnnotatedConstructors(implementationClass);
        if (amountAnnotatedConstructors == 0 && !(isThereDefaultConstructor(implementationClass))) {
            logger.log(Level.SEVERE, "There aren't any default constructors");
            throw new ConstructorNotFoundException("Default constructor hasn't been found");
        }
        if (amountAnnotatedConstructors > 1) {
            logger.log(Level.SEVERE, "There are more than 1 @Inject-annotated constructors");
            throw new TooManyConstructorsException("There are more than 1 constructor with @Inject annotation");
        }
        return pullBean(implementationClass);
    }

    private int countAnnotatedConstructors(Class<?> clazz) {
        List<Constructor> constructors = Arrays.stream(clazz.getDeclaredConstructors())
                .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
        return constructors.size();
    }

    private boolean isThereDefaultConstructor(Class<?> clazz) {
        boolean result = false;
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (constructor.getParameterCount() == 0) {
                result = true;
            }
        }
        return result;
    }

    private <T> T pullBean(Class<?> implementationClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        T bean = null;
        Constructor<?>[] declaredConstructors = implementationClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if (Modifier.isPrivate(declaredConstructor.getModifiers())) {
                declaredConstructor.setAccessible(true);
                Field[] fields = implementationClass.getFields();
                for (Field field : fields) {
                    if (field.getName() == implementationClass.getName()) {
                        field.setAccessible(true);
                        bean = (T) field.get(implementationClass);
                    }
                }
            } else {
                bean = (T) implementationClass.getDeclaredConstructor().newInstance();
            }
        }
        return bean;
    }

    public BeanConfigurator getBeanConfigurator() {
        return beanConfigurator;
    }
}