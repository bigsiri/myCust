package com.bigeye.mycust.appdirect.exception;

import com.bigeye.mycust.appdirect.enums.EventResultErrorMessageEnum;

public class EventResultException extends RuntimeException{
	private static final long serialVersionUID = -3458746098141860834L;
	
	private EventResultErrorMessageEnum error;
	private String message;

	public EventResultException(EventResultErrorMessageEnum error, String message) {
		super();
		this.error = error;
		this.message =  message;
	}

	public EventResultErrorMessageEnum getError() {
		return error;
	}

	public void setError(EventResultErrorMessageEnum error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
