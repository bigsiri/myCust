package com.bigeye.mycust.web.view.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(Include.NON_NULL)
@JsonTypeName("Response")
public final class ResponseJson implements Serializable {

	private static final long serialVersionUID = 2406161152587012949L;

	private ResponseStatus code;

	private String message;

	public ResponseStatus getCode() {
		return code;
	}

	public void setCode(ResponseStatus code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
