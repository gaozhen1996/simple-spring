package com.gz.javastudy.spring;


import com.gz.javastudy.spring.bean.AnnotationConfigApplicationContext;
import com.gz.javastudy.spring.context.ComponentScan;
import com.gz.javastudy.spring.test.MessageService1;
import com.gz.javastudy.spring.test.MessageService2;
import com.gz.javastudy.spring.test.MessageService3;

@ComponentScan("com.gz.javastudy.spring")
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Application.class);
		MessageService1 messageService = context.getBean("messageService1",MessageService1.class);
		System.out.println(messageService.messageService2);
		
		MessageService2 messageService2 = context.getBean("messageService2",MessageService2.class);
		System.out.println(messageService2.messageService3);
		
		MessageService3 messageService3 = context.getBean("messageService3",MessageService3.class);
		System.out.println(messageService3.messageService1);

	}
}
