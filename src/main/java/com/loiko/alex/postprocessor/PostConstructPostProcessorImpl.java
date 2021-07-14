package com.loiko.alex.postprocessor;

import com.loiko.alex.annotation.PostConstruct;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class PostConstructPostProcessorImpl implements PostProcessor {

    @Override
    public void process(Object bean) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
             method.invoke(bean);
            }
        }
    }
}