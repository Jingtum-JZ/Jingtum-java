package com.jingtum.exception;

public abstract class JingtumException extends Exception {

	public JingtumException(String message) {
		super(message, null);
	}

	public JingtumException(String message, Throwable e) {
		super(message, e);
	}

	private static final long serialVersionUID = 1L;

}
