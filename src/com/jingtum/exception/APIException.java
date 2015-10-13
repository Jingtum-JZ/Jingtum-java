package com.jingtum.exception;

public class APIException extends JingtumException {

	private static final long serialVersionUID = 1L;

	public APIException(String message, Throwable e) {
		super(message, e);
	}

}
