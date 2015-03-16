package com.bigeye.mycust.web.view.json;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class CustomJacksonObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 2198177200621573533L;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	public CustomJacksonObjectMapper() {
		super();
		setDateFormat(dateFormat);
	}
}