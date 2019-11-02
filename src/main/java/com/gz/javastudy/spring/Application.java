package com.gz.javastudy.spring;

import com.gz.javastudy.spring.bean.AnnotationConfigApplicationContext;
import com.gz.javastudy.spring.context.ComponentScan;

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
			System.out.println(resourceUrls.nextElement());
			while (resourceUrls.hasMoreElements()) {
				URL url = resourceUrls.nextElement();
				System.out.println("URL:"+url);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
