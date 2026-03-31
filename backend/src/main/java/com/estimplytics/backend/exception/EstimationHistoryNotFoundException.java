package com.estimplytics.backend.exception;

public class EstimationHistoryNotFoundException extends RuntimeException {
    public EstimationHistoryNotFoundException() {
        super();
    }

    public EstimationHistoryNotFoundException(String message) {
        super(message);
    }

    public EstimationHistoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}