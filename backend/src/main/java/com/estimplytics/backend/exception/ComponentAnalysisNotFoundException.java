package com.estimplytics.backend.exception;

public class ComponentAnalysisNotFoundException extends RuntimeException {
    public ComponentAnalysisNotFoundException() {
        super();
    }

    public ComponentAnalysisNotFoundException(String message) {
        super(message);
    }

    public ComponentAnalysisNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}