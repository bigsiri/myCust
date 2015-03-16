package com.bigeye.mycust.web.context;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.bigeye.mycust.service.ContextInitializerService;

public final class SpringMvcContextLoaderListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);

		final WebApplicationContext ctx = getCurrentWebApplicationContext();
		final ContextInitializerService contextInitializerService = ctx.getBean(ContextInitializerService.class);
		contextInitializerService.contextInitialized(event);
	}
}
