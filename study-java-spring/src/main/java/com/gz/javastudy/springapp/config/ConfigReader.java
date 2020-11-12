package com.gz.javastudy.springapp.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	private static final String LOCATION = "application.properties";
	
	// 配置信息
	private Properties properties = new Properties();
	
	public ConfigReader() {
		InputStream in = null;
		try {
			in = this.getClass().getClassLoader().getResourceAsStream(LOCATION);
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getconfigValueByKey(String key) {
		return properties.getProperty(key);
	}
}
