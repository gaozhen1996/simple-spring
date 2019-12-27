package com.gz.javastudy.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationConfig {
	
	@Bean
	public ConfigReader getConfigReader() {
		return new ConfigReader();
	}
}
