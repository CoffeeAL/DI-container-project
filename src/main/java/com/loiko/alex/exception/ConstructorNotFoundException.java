package com.loiko.alex.exception;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class ConstructorNotFoundException extends RuntimeException {

    public ConstructorNotFoundException() {
    }

    public ConstructorNotFoundException(String message) {
        super(message);
    }

    public ConstructorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstructorNotFoundException(Throwable cause) {
        super(cause);
    }
}