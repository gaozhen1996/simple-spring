package com.gz.javastudy.dubbopro;

import org.apache.dubbo.config.annotation.Service;

import com.gz.javastudy.dubboapi.TicketService;

@SuppressWarnings("deprecation")
@Service(version = "1.0.0")
public class DefaultTicketService implements TicketService {

	private static int num = 100;
	
	@Override
	public int init(int num) {
		DefaultTicketService.num = num;
		return num;
	}

	@Override
	public int del() {
		DefaultTicketService.num--;
		return 0;
	}

	@Override
	public int get() {
		return DefaultTicketService.num;
	}
	
	
	
}
