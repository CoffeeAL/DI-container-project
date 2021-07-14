package com.loiko.alex.configurator;

import com.loiko.alex.reflection.ReflectionDemonstrator;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public interface BeanConfigurator {

    <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);

    ReflectionDemonstrator getDemonstrator();
}