package com.gz.javastudy.spring.test;

import com.gz.javastudy.spring.context.Autowired;
import com.gz.javastudy.spring.context.Service;

@Service
public class MessageService1 {
	@Autowired
	public MessageService2 messageService2; 
}
