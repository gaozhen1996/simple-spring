package com.gz.javastudy.sellticket;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.gz.javastudy.sellticket.coreframe.SpringApplication;

@Configuration
@ComponentScan("com.gz.javastudy.sellticket")
public class App{
	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}
	
}
