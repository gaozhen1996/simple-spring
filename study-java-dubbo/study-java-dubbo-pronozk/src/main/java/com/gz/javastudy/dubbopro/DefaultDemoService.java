package com.gz.javastudy.dubbopro;

import org.apache.dubbo.config.annotation.Service;

import com.gz.javastudy.dubboapi.DemoService;

@SuppressWarnings("deprecation")
@Service(version = "1.0.0")
public class DefaultDemoService implements DemoService {

	@Override
	public int add(int a, int b) {
		return a+b;
	}
}
