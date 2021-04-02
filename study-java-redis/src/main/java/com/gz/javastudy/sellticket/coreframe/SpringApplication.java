package com.gz.javastudy.sellticket.coreframe;

import java.io.File;


import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class SpringApplication {
	
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "src/main/resources";

	@SuppressWarnings("rawtypes")
	public static AnnotationConfigWebApplicationContext run(Class registerClass,int port) {
		Tomcat tomcat = new Tomcat();
		AnnotationConfigWebApplicationContext context = null;
		try {
			tomcat.setPort(port);
			tomcat.addContext("/", WEB_ROOT);
			
	    	System.out.println("====================init===============================");
	    	System.out.println("WEB_ROOT:"+WEB_ROOT);
	        // Load Spring web application configuration
	        context = new AnnotationConfigWebApplicationContext();
	        context.register(registerClass);

	        // Create and register the DispatcherServlet
	        DispatcherServlet servlet = new DispatcherServlet(context);
	        /**
	         * 代替web.xml文件
	         * <servlet>   
  　　　　    *   <servlet-name>MyServlet</servlet-name>   
  　　　　    *   <servlet-class>com.mycompany.mypackage.MyServlet</servlet-class>   
  　　　　    *   <load-on-startup>1</load-on-startup>   
  　　       * </servlet>  
	         */
	        Wrapper mvc = tomcat.addServlet("/", "app", servlet);
	        mvc.setLoadOnStartup(1);
	        mvc.addMapping("/");
	        /**
	         * tomcat start的时候会执行servlet的init方法
	         */
			tomcat.start();	
			tomcat.getServer().await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return context;
	}
}
