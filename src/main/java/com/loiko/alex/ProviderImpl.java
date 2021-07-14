package com.loiko.alex;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class ProviderImpl implements Provider<ProviderImpl> {

    private static ProviderImpl PROVIDER = new ProviderImpl();

    private ProviderImpl() {
    }

    @Override
    public ProviderImpl getInstance() {
        return PROVIDER;
    }
}