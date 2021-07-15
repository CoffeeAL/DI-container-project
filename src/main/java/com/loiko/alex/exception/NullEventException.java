package com.loiko.alex.exception;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class NullEventException extends RuntimeException {

    public NullEventException() {
    }

    public NullEventException(String message) {
        super(message);
    }

    public NullEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullEventException(Throwable cause) {
        super(cause);
    }
}