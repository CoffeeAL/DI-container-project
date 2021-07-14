package com.loiko.alex.postprocessor;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public interface PostProcessor {

    void process(Object bean) throws InvocationTargetException, IllegalAccessException;
}