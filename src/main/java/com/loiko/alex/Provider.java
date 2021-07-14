package com.loiko.alex;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public interface Provider<T> {

    T getInstance();
}