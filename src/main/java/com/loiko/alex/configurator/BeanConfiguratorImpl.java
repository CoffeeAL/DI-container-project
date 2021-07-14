package com.loiko.alex.configurator;

import com.loiko.alex.reflection.ReflectionDemonstrator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class BeanConfiguratorImpl implements BeanConfigurator {

    private final ReflectionDemonstrator demonstrator;
    private final Map<Class, Class> interfaceToImplementation;

    public BeanConfiguratorImpl(String packageToScan, Map<Class, Class> interfaceToImplementation) {
        this.demonstrator = new ReflectionDemonstrator(packageToScan);
        this.interfaceToImplementation = new HashMap<>(interfaceToImplementation);
    }

    public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass) {
        System.out.println();

        return interfaceToImplementation.computeIfAbsent(interfaceClass, clazz -> {
            Set<Class<?>> allSubtypes = demonstrator.getSubclasses(interfaceClass);
            if (allSubtypes.size() != 1) {
                throw new RuntimeException("Amount of implementations is 0 or more than 1.");
            }
            return (Class<? extends T>) allSubtypes.stream().findFirst().get();
        });
    }

    public ReflectionDemonstrator getDemonstrator() {
        return demonstrator;
    }
}