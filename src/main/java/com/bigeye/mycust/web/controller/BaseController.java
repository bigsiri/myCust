package com.bigeye.mycust.web.controller;

import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public abstract class BaseController {

	/** The Constant SYSTEM_MODEL_KEY. */
	public static final String SYSTEM_MODEL_KEY = "system";

	/** The Constant STATUS_MESSAGE_KEY. */
	public static final String STATUS_MESSAGE_KEY = "statusMessageKey";

	protected static final String STATUS_KEY = "status";

	protected void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		final LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if (localeResolver != null) {
			localeResolver.setLocale(request, response, locale);
		}
	}

	protected Locale getLocale(HttpServletRequest request) {
		Locale locale = new Locale("en", "CA");

		final LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if (localeResolver != null) {
			locale = localeResolver.resolveLocale(request);
		}

		return locale;
	}

	@ModelAttribute
	public void init(ModelMap model, HttpServletRequest request) {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {

			final Locale locale = getLocale(request);
			String language = null;
			if (locale.getLanguage().equals(Locale.FRENCH.getLanguage())) {
				language = Locale.ENGLISH.getLanguage();
			} else {
				language = Locale.FRENCH.getLanguage();
			}
			final String requestUrl = request.getRequestURL().toString();

			final StringBuffer parameters = new StringBuffer();
			if (request.getMethod().equals("GET")) {
				final Set<String> params = request.getParameterMap().keySet();
				for (final String paramName : params) {
					if (!paramName.equals("locale")) {
						final String paramValue = request.getParameter(paramName);
						parameters.append(paramName).append("=").append(paramValue).append("&");
					}
				}
			}

			model.put("toggleRequestUrl", requestUrl + "?" + parameters.toString() + "locale=" + language);
		}
	}
}
