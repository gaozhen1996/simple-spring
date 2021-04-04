package com.gz.javastudy.sellticket;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.gz.javastudy.sellticket.coreframe.SpringApplication;

@Configuration
@ComponentScan("com.gz.javastudy.sellticket")
@MapperScan("com.gz.javastudy.sellticket.repository")
public class App{
	
	public static final int PORT = 8081;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class,PORT);
	}
	
}
