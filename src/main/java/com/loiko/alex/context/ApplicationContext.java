package com.loiko.alex.context;

import com.loiko.alex.annotation.Inject;
import com.loiko.alex.bean.BeanFactory;
import com.loiko.alex.postprocessor.PostConstructPostProcessorImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class ApplicationContext {

    @Inject
    private BeanFactory factory;
    private final Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
    }

    public <T> T getBean(Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        if (beans.containsKey(clazz)) {
            return (T) beans.get(clazz);
        }
        T bean = factory.getBean(clazz);
        runPostProcess(bean);
        beans.put(clazz, bean);
        return bean;
    }

    private void runPostProcess(Object bean) throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException, InstantiationException {
        Set<Class<?>> subtypes = factory.getBeanConfigurator().getDemonstrator()
                .getSubclasses(PostConstructPostProcessorImpl.class);
        for (Class processor : subtypes) {
            PostConstructPostProcessorImpl postprocessor = (PostConstructPostProcessorImpl) processor.getDeclaredConstructor().newInstance();
            postprocessor.process(bean);
        }
    }

    public void setFactory(BeanFactory factory) {
        this.factory = factory;
    }
}