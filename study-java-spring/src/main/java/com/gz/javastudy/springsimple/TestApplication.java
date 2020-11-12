package com.gz.javastudy.springsimple;



import com.gz.javastudy.springsimple.framework.AnnotationConfigApplicationContext;
import com.gz.javastudy.springsimple.framework.MyAutowired;
import com.gz.javastudy.springsimple.framework.MyComponentScan;
import com.gz.javastudy.springsimple.framework.MyService;

@MyService
@MyComponentScan("com.gz.javastudy.springsimple")
public class TestApplication {

	@MyAutowired
	TestService testService;
	
	public static void main(String[] args) throws  Exception {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(TestApplication.class);
		
		TestApplication app = context.getBean("testApplication");
		System.out.println(app.testService);
		
	}
}
