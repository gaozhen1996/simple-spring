package com.gz.javastudy.spring.test;

import com.gz.javastudy.spring.context.Autowired;
import com.gz.javastudy.spring.context.Service;

@Service
public class MessageService3 {
	@Autowired
	public MessageService1 messageService1;
}
