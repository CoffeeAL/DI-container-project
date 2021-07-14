package com.loiko.alex;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public interface Injector {

    <T> Provider<T> getProvider(Class<T> type);

    <T> void bind(Class<T> intf, Class<? extends T> impl);

    <T> void bindSingleton(Class<T> intf, Class<? extends T> impl);
}