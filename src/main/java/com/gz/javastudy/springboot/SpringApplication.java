package com.gz.javastudy.springboot;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SpringApplication {
	public static void run() {
		Tomcat tomcat = new Tomcat();
		try {
			tomcat.setPort(8080);
			tomcat.start();
			tomcat.getServer().await();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
}
