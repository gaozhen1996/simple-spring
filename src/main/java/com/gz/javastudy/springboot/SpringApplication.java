package com.gz.javastudy.springboot;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

public class SpringApplication {
	
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "src/main/resources/tomcat";

	public static void run() {
		Tomcat tomcat = new Tomcat();
		try {
			tomcat.setPort(8080);
			tomcat.addWebapp("/", WEB_ROOT);
			tomcat.start();	
			tomcat.getServer().await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
