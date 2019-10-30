package com.gz.spring;

import com.gz.spring.bean.AnnotationConfigApplicationContext;
import com.gz.spring.context.ComponentScan;

@ComponentScan("com.gz.spring")
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Application.class);
		System.out.println(context);
	}
}
