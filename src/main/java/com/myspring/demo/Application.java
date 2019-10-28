package com.myspring.demo;



import com.myspring.demo.annotation.MyAutowired;
import com.myspring.demo.annotation.MyService;
import com.myspring.demo.service.TestService;
import com.myspring.framework.AnnotationConfigApplicationContext;

@MyService
public class Application {

	@MyAutowired("testService")
	TestService testService;
	
	public static void main(String[] args) throws  Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.init();
		Application app = context.getBean("application");
		System.out.println(app);
	}
}
