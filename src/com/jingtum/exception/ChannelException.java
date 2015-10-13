package com.jingtum.exception;

public class ChannelException extends JingtumException {

	private static final long serialVersionUID = 1L;

	private final String param;

	public ChannelException(String message, String param, Throwable e) {
		super(message, e);
		this.param = param;
	}

	public String getParam() {
		return param;
	}

}
