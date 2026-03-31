package com.estimplytics.backend.exception;

public class ImpactAnalysisNotFoundException extends RuntimeException {
    public ImpactAnalysisNotFoundException() {
        super();
    }

    public ImpactAnalysisNotFoundException(String message) {
        super(message);
    }

    public ImpactAnalysisNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}