package com.gz.javastudy.spring;

import com.gz.javastudy.spring.bean.AnnotationConfigApplicationContext;
import com.gz.javastudy.spring.context.ComponentScan;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;

@ComponentScan("com.gz.javastudy.spring")
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Application.class);
		System.out.println(context);

		try {
			Enumeration<URL> resourceUrls = ClassLoader.getSystemResources("com/gz/javastudy/spring");
			while (resourceUrls.hasMoreElements()) {
				URL url = resourceUrls.nextElement();
				File dir = new File(url.getFile());
				for (File file:dir.listFiles()) {
					System.out.println(file);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
