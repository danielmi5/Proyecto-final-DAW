package com.estimplytics.backend.exception;

public class RedmineIntegrationException extends RuntimeException {

	private final ErrorType errorType;

	public RedmineIntegrationException(String message, ErrorType errorType) {
		super(message);
		this.errorType = errorType;
	}

	public RedmineIntegrationException(String message, Throwable cause, ErrorType errorType) {
		super(message, cause);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public enum ErrorType {
		NETWORK_ERROR,
		INVALID_CREDENTIALS,
		INVALID_PAYLOAD,
		TIMEOUT
	}
}
