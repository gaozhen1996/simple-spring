package com.gz.javastudy.spring.test;

import com.gz.javastudy.spring.context.Autowired;
import com.gz.javastudy.spring.context.Service;

@Service
public class MessageService2 {
	@Autowired
	public MessageService3 messageService3;
}
