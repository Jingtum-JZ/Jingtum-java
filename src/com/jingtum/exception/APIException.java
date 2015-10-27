package com.jingtum.exception;
/**
 * @author jzhao
 * @version 1.0
 * Default error
 */
public class APIException extends JingtumException {
	private static final long serialVersionUID = 1L;
	public APIException(String message, Throwable e) {
		super(message, e);
	}
}
