package com.gz.javastudy.springsimple.demo;



import com.gz.javastudy.springsimple.demo.annotation.MyAutowired;
import com.gz.javastudy.springsimple.demo.annotation.MyService;
import com.gz.javastudy.springsimple.demo.service.TestService;
import com.gz.javastudy.springsimple.framework.AnnotationConfigApplicationContext;

@MyService
public class TestApplication {

	@MyAutowired("testService")
	TestService testService;
	
	public static void main(String[] args) throws  Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.init();
		TestApplication app = context.getBean("testApplication");
		System.out.println(app);
	}
}
