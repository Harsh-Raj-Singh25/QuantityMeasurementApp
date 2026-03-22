package com.quantityMeasurement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
	private static final Properties properties = new Properties();

	static {
		try (InputStream input = ApplicationConfig.class.getClassLoader()
				.getResourceAsStream("application.properties")) {
			if (input != null) {
				properties.load(input);
			}
		} catch (Exception ex) {
			logger.error("Error loading properties", ex);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
