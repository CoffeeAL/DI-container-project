package com.loiko.alex;

import com.loiko.alex.annotation.Inject;
import com.loiko.alex.annotation.PostConstruct;
import com.loiko.alex.logger.LoggerFactory;

import java.util.logging.Logger;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class InjectorImpl implements Injector {

    private Provider provider;
    private static Logger logger = LoggerFactory.getLogger();

//    @Inject
//    public InjectorImpl() {
//    }
//
//    @Inject
    public InjectorImpl(Provider provider) {
        this.provider = provider;
    }

    @PostConstruct
    public void callPostConstructMethod() {
        logger.info("Post construct method called");
    }

    public <T> Provider<T> getProvider(Class<T> type) {
        return provider;
    }

    public <T> void bind(Class<T> intf, Class<? extends T> impl) {

    }

    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) {

    }

    public void doSuccessfulInjection() {
        logger.info("Successfull injection has been done");
    }

    public Provider getProvider() {
        return provider;
    }
}