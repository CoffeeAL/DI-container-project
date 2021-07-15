package com.loiko.alex.exception;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class NotPositiveIdException extends RuntimeException {

    public NotPositiveIdException() {
    }

    public NotPositiveIdException(String message) {
        super(message);
    }

    public NotPositiveIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotPositiveIdException(Throwable cause) {
        super(cause);
    }
}