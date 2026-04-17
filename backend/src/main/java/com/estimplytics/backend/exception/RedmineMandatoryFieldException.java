package com.estimplytics.backend.exception;

public class RedmineMandatoryFieldException extends RuntimeException {
    public RedmineMandatoryFieldException(String message) {
        super(message);
    }
}
