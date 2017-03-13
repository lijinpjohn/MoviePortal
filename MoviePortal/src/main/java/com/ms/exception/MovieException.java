package com.ms.exception;

public class MovieException extends Exception {
	private static final long serialVersionUID = 1L;

	public MovieException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public MovieException() {
		super();
	}

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}
}
