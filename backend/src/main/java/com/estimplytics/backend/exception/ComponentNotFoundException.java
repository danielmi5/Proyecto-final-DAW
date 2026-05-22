package com.estimplytics.backend.exception;

public class ComponentNotFoundException extends RuntimeException {
    public ComponentNotFoundException() {
        super();
    }

    public ComponentNotFoundException(String message) {
        super(message);
    }

    public ComponentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}