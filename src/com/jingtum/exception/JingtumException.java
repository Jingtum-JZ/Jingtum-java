package com.jingtum.exception;
/**
 * @author jzhao
 * @version 1.0
 * Base exception class
 */
public abstract class JingtumException extends Exception {
	public JingtumException(String message) {
		super(message, null);
	}
	public JingtumException(String message, Throwable e) {
		super(message, e);
	}
	private static final long serialVersionUID = 1L;
}
