package com.estimplytics.backend.exception;

public class ImpactAnalysisHistoryNotFoundException extends RuntimeException {
    public ImpactAnalysisHistoryNotFoundException() {
        super();
    }

    public ImpactAnalysisHistoryNotFoundException(String message) {
        super(message);
    }

    public ImpactAnalysisHistoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}