package com.gz.spring.bean;


public interface BeanFactory {
	
	<T> T getBean(String name, Class<T> requiredType);
	
	/**
	 * 注册BeanDefinition到bean的容器中
	 * @param beanName
	 * @param beanDefinition
	 */
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
