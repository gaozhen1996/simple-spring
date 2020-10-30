package com.gz.javastudy.springboot;

import java.io.File;


import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringApplication {
	
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "src/main/resources/tomcat";

	public static void run() {
		Tomcat tomcat = new Tomcat();
		try {
			tomcat.setPort(8080);
			tomcat.addContext("/", WEB_ROOT);
			
	    	System.out.println("====================init===============================");
	        // Load Spring web application configuration
	        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	        context.register(AppConfig.class);
	        
	        // Create and register the DispatcherServlet
	        DispatcherServlet servlet = new DispatcherServlet(context);
	        Wrapper mvc = tomcat.addServlet("/", "app", servlet);
	        mvc.setLoadOnStartup(1);
	        mvc.addMapping("/");
			
			
			tomcat.start();	
			tomcat.getServer().await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
