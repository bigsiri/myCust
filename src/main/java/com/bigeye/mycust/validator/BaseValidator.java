package com.bigeye.mycust.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseValidator {

	@Autowired
	private MessageSource messageSource;

	private Locale locale;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
