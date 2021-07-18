package com.loiko.alex;

import com.loiko.alex.annotation.Inject;
import com.loiko.alex.annotation.PostConstruct;
import com.loiko.alex.context.ApplicationContext;
import com.loiko.alex.exception.BindingRegistrationException;
import com.loiko.alex.logger.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class InjectorImpl implements Injector {

    private Provider provider;
    private static Logger logger = LoggerFactory.getLogger();

    public InjectorImpl() {
    }

    @Inject
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
        if (intf.isInterface()) {
            Map<Class, Set<Object>> interfacesWithTheirImplementations = ApplicationContext.getInterfacesWithTheirImplementations();
            Set<Class> keys = interfacesWithTheirImplementations.keySet();
            if (keys.contains(intf)) {
                Class clazz = keys.stream()
                        .filter(key -> key == intf)
                        .findFirst()
                        .get();
                interfacesWithTheirImplementations.get(clazz).add(impl);
            } else {
                Set<Object> implementations = new HashSet<>();
                implementations.add(impl);
                interfacesWithTheirImplementations.put(intf, implementations);
            }
        } else {
            throw new BindingRegistrationException("The class should be bind to an interface");
        }
    }

    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) {
        if (!intf.isInterface()) {
            throw new BindingRegistrationException("The class should be bind to an interface");
        } else if (!isSingleton(impl)) {
            throw new BindingRegistrationException("Not a singleton");
        } else {
            bind(intf, impl);
        }
    }

    private boolean isSingleton(Class<?> clazz) {
        boolean isSingleton = false;
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (Modifier.isPrivate(constructor.getModifiers())) {
                isSingleton = true;
            }
        }
        return isSingleton;
    }

    public void doSuccessfulInjection() {
        logger.info("Successfull injection has been done");
    }

    public Provider getProvider() {
        return provider;
    }
}