package com.gz.javastudy.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentConfig {
	
	@Bean
	public ConfigReader getConfigReader() {
		return new ConfigReader();
	}
}
