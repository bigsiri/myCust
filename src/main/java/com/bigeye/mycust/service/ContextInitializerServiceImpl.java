package com.bigeye.mycust.service;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bigeye.mycust.model.Language;
import com.bigeye.mycust.model.user.User;

@Service
public final class ContextInitializerServiceImpl implements ContextInitializerService {

	private static final Log LOG = LogFactory.getLog(ContextInitializerServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Value("${db.bootstrapData.user}")
	private Boolean bootstrapUserActif;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		if (Boolean.TRUE.equals(bootstrapUserActif)) {
			createUsers();
		}
		
		final Manifest manifest = new Manifest();
		String buildNumber = "";
		String versionNumber = "";

		try {
			manifest.read(event.getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF"));
			final Attributes attributes = manifest.getMainAttributes();
			if (attributes != null) {
				versionNumber = attributes.getValue("Implementation-Version");
				buildNumber = attributes.getValue("Implementation-Build");
			}
		} catch (final IOException e) {
		}

		event.getServletContext().setAttribute("buildNumber", buildNumber);
		event.getServletContext().setAttribute("versionNumber", versionNumber);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("buildNumber: " + buildNumber);
			LOG.info("versionNumber: " + versionNumber);
		}
	}

	private void createUsers() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Bootstrap Users");
		}

		final String[] adminRoles = { "ROLE_ADMIN" };
		final User user1 = new User("test@bigeye.ca", "admin", "Ismaila", "CAMARA", true, adminRoles,
				Language.en);
		User userDb = userService.findByLogin("test@bigeye.ca");
		if(userDb == null){
			userService.saveOrUpdate(user1);
		}
		
	}
}
