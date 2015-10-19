package com.jingtum.exception;
/**
 * @author jzhao
 * @version 1.0
 */
public class APIConnectionException extends JingtumException {
	private static final long serialVersionUID = 1L;
	public APIConnectionException(String message) {
		super(message);
	}
	public APIConnectionException(String message, Throwable e) {
		super(message, e);
	}
}
