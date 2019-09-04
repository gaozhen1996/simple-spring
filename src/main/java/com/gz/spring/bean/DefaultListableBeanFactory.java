package com.gz.spring.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DefaultListableBeanFactory implements BeanFactory{

	//存beanDefinition,key是beanName
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
	
	@Override
	public <T> T getBean(String name, Class<T> requiredType) {
		return null;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(beanName, beanDefinition);
	}

}
