package com.bigeye.mycust.web.view.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseStatus {

	SUCCESS(0), ERROR(1);

	private Integer value;

	private ResponseStatus(int value) {
		this.value = Integer.valueOf(value);
	}

	@JsonValue
	public Integer value() {
		return value;
	}

	@JsonCreator
	public static ResponseStatus forValue(Integer v) {
		if (ResponseStatus.SUCCESS.value.equals(v)) {
			return SUCCESS;
		}
		if (ResponseStatus.ERROR.value.equals(v)) {
			return ERROR;
		}

		return null;
	}

}
