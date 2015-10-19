package com.jingtum.exception;
/**
 * @author jzhao
 * @version 1.0
 * Http error code 400 and 404
 */
public class InvalidRequestException extends JingtumException {
	private static final long serialVersionUID = 1L;
	private final String param;
	public InvalidRequestException(String message, String param, Throwable e) {
		super(message, e);
		this.param = param;
	}
	public String getParam() {
		return param;
	}
}
