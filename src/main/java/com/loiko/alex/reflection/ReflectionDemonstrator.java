package com.loiko.alex.reflection;

import com.loiko.alex.helper.Accessor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class ReflectionDemonstrator {

    private String packageToScan;

    public ReflectionDemonstrator(String packageToScan) {
        this.packageToScan = packageToScan;
    }

    public <T> Set<Class<?>> getSubclasses(Class<T> clazz) {
        Set<Class<?>> setClasses = new HashSet<>();
        if (clazz.isInterface()) {
            Set<Class> allClasses = new Accessor().findAllClassesUsingClassLoader("com.loiko.alex");
            for (Class<?> classic : allClasses) {
                Class<?>[] interfaces = classic.getInterfaces();
                for (Class<?> intrfc: interfaces) {
                    if (intrfc.equals(clazz)) {
                        setClasses.add(classic);
                    }
                }
            }
        } else {
            Class<?>[] classes = clazz.getClasses();
            for (Class<?> clazzic : classes) {
                setClasses.add(clazzic);
            }
        }
        return setClasses;
    }
}