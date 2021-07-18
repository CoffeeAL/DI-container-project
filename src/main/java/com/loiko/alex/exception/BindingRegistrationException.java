package com.loiko.alex.exception;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class BindingRegistrationException extends RuntimeException {

    public BindingRegistrationException() {
    }

    public BindingRegistrationException(String message) {
        super(message);
    }

    public BindingRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindingRegistrationException(Throwable cause) {
        super(cause);
    }
}