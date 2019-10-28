package com.gz.spring.bean;


public interface BeanFactory {
	
	<T> T getBean(String name, Class<T> requiredType);

}
