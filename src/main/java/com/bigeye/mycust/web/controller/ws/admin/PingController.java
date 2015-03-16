package com.bigeye.mycust.web.controller.ws.admin;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import com.bigeye.mycust.service.PingService;
import com.bigeye.mycust.web.controller.BaseController;

@RestController
public final class PingController extends BaseController implements ServletContextAware {

	@Autowired
	private PingService pingService;
	
	private ServletContext servletContext;

	private static final int mb = 1024 * 1024;

	@RequestMapping(value = { "/ws/ping.json", "/ws/secure/ping.json" }, produces = {"application/json"})
	public Version ping(HttpServletRequest request) throws IOException {

		String implementationVersion = "";
		String buildNumber = "";
		String buildDate = "";

		final Manifest manifest = new Manifest();

		manifest.read(servletContext.getResourceAsStream("/META-INF/MANIFEST.MF"));
		final Attributes attributes = manifest.getMainAttributes();
		if (attributes != null) {
			implementationVersion = attributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION.toString());
			buildNumber = attributes.getValue("Implementation-Build");
			buildDate = attributes.getValue("Built-Date");
		}

		final Version v = new Version();
		v.setBuildNumber(buildNumber);
		v.setBuildDate(buildDate);
		v.setVersion(implementationVersion);

		final Date currentDatabaseDate = pingService.getCurrentDate();
		if (currentDatabaseDate != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			v.setCurrentDatabaseDate(formatter.format(currentDatabaseDate));
		}
		
		return v;
	}

	@RequestMapping(value = "/ws/stats.json", produces = {"application/json"})
	public Stats stats(HttpServletRequest request) throws IOException {

		System.gc();

		// Getting the runtime reference from system
		final Runtime runtime = Runtime.getRuntime();

		final Stats stats = new Stats();
		// Print used memory
		stats.setUsedMemory((runtime.totalMemory() - runtime.freeMemory()) / mb);
		// Print free memory
		stats.setFreeMemory(runtime.freeMemory() / mb);
		// Print total available memory
		stats.setTotalMemory(runtime.totalMemory() / mb);
		stats.setMaxMemory(runtime.maxMemory() / mb);

		return stats;
	}

	public static final class Stats {
		private long usedMemory;
		private long freeMemory;
		private long totalMemory;
		private long maxMemory;

		public long getUsedMemory() {
			return usedMemory;
		}

		public void setUsedMemory(long usedMemory) {
			this.usedMemory = usedMemory;
		}

		public long getFreeMemory() {
			return freeMemory;
		}

		public void setFreeMemory(long freeMemory) {
			this.freeMemory = freeMemory;
		}

		public long getTotalMemory() {
			return totalMemory;
		}

		public void setTotalMemory(long totalMemory) {
			this.totalMemory = totalMemory;
		}

		public long getMaxMemory() {
			return maxMemory;
		}

		public void setMaxMemory(long maxMemory) {
			this.maxMemory = maxMemory;
		}
	}

	public static final class Version implements Serializable {
		private static final long serialVersionUID = -3882509541011718731L;

		private String version;
		private String buildNumber;
		private String buildDate;
		private String currentDatabaseDate;

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getBuildNumber() {
			return buildNumber;
		}

		public void setBuildNumber(String buildNumber) {
			this.buildNumber = buildNumber;
		}

		public String getBuildDate() {
			return buildDate;
		}

		public void setBuildDate(String buildDate) {
			this.buildDate = buildDate;
		}
		
		public String getCurrentDatabaseDate() {
			return currentDatabaseDate;
		}

		public void setCurrentDatabaseDate(String currentDatabaseDate) {
			this.currentDatabaseDate = currentDatabaseDate;
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
