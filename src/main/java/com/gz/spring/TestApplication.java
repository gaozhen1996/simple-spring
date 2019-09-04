package com.gz.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.myspring.demo.service.TestService;

@ComponentScan("com.myspring.demo")
public class TestApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestApplication.class);
		TestService testService = (TestService) context.getBean("testService");
		testService.query();
		System.out.println("app:"+context.getBean("testApplication"));
		context.close();
		
	}

}
