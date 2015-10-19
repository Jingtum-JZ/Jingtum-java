package com.jingtum.exception;
/**
 * @author jzhao
 * @version 1.0
 * Http error code 401
 */
public class AuthenticationException extends JingtumException {
	public AuthenticationException(String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;
}
