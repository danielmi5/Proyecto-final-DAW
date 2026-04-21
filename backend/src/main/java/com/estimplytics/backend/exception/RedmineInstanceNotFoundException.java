package com.estimplytics.backend.exception;

public class RedmineInstanceNotFoundException extends RuntimeException {

    public RedmineInstanceNotFoundException(String message) {
        super(message);
    }
}
