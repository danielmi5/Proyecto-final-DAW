package com.estimplytics.backend.exception;

public class EstimationNotFoundException extends RuntimeException {
    public EstimationNotFoundException() {
        super();
    }

    public EstimationNotFoundException(String message) {
        super(message);
    }

    public EstimationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}