package com.gz.spring.bean;


/**
 * BeanDefinition 的 注册器
 * @author gaozhen
 */
public interface BeanDefinitionRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
	
}
