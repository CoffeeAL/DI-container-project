package com.loiko.alex.configuration;

import java.util.Map;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public interface Configuration {

    String getPackageToScan();

    Map<Class, Class> interfaceToImplementations();
}