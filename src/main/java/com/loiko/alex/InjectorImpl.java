package com.loiko.alex;

import com.loiko.alex.annotation.Inject;
import com.loiko.alex.annotation.PostConstruct;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class InjectorImpl implements Injector {

    @Inject
    private Provider provider;

    @PostConstruct
    public void callPostConstructMethod() {
        System.out.println("Post construct method has been called.");
    }

    public <T> Provider<T> getProvider(Class<T> type) {
        return provider;
    }

    public <T> void bind(Class<T> intf, Class<? extends T> impl) {

    }

    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) {

    }

    public void doSuccessfulInjection() {
        System.out.println("Successful injection");
    }

    public Provider getProvider() {
        return provider;
    }
}