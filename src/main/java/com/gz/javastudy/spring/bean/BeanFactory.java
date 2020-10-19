package com.gz.javastudy.spring.bean;


public interface BeanFactory {
	
	<T> T getBean(String name, Class<T> requiredType);
	
	void preInstantiateSingletons();
}
