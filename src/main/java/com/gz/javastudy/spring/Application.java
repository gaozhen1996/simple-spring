package com.gz.javastudy.spring;

import com.gz.javastudy.spring.bean.AnnotationConfigApplicationContext;
import com.gz.javastudy.spring.context.ComponentScan;
import com.gz.javastudy.spring.test.Config;

@ComponentScan("com.gz.javastudy.spring")
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Application.class);
		System.out.println(context.getBeanDefinition("config").isLazyInit());
		System.out.println(context.getBean("config",Config.class));
	}
}
