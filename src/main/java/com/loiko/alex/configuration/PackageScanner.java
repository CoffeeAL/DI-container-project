package com.loiko.alex.configuration;

import com.loiko.alex.Injector;
import com.loiko.alex.InjectorImpl;
import com.loiko.alex.context.ApplicationContext;

import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class PackageScanner implements Configuration {

    @Override
    public String getPackageToScan() {
        return "com.loiko.alex";
    }

    @Override
    public Map<Class, Class> interfaceToImplementations() {
        return Map.of(Injector.class, InjectorImpl.class);
    }

    public Map<Class, Set<Object>> interfacesImplementationsMap() {
        return ApplicationContext.getInterfacesWithTheirImplementations();
    }
}