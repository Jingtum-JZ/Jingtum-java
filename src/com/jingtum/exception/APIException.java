package com.jingtum.exception;
/**
 * @author jzhao
 * @version 1.0
 */
public class APIException extends JingtumException {
	private static final long serialVersionUID = 1L;
	public APIException(String message, Throwable e) {
		super(message, e);
	}
}
