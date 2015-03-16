package com.bigeye.mycust.appdirect.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.bigeye.mycust.appdirect.enums.EventResultErrorMessageEnum;

@XmlRootElement(name="result")
public class EventResult {

	private Boolean success;
	private String errorCode;
	private String message;
	private String accountIdentifier;
	
	public EventResult() {
	}
	
	public static EventResult success(Boolean success) {
		EventResult eventResult = new EventResult();
		eventResult.setSuccess(success);
		return eventResult;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	} 
	
	public static EventResult success(String accountIdentifier,String message)
	{
		EventResult eventResult = new EventResult();
		eventResult.setAccountIdentifier(accountIdentifier);
		eventResult.setMessage(message);
		eventResult.setSuccess(Boolean.TRUE);

		return eventResult;
	}
	
	public static EventResult error(EventResultErrorMessageEnum error, String message)
	{
		EventResult eventResult = new EventResult();
		eventResult.setSuccess(Boolean.FALSE);
		eventResult.setErrorCode(error.name());
		eventResult.setMessage(message);

		return eventResult;
	}

	
	
}
