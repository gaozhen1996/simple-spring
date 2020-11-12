package com.gz.javastudy.spring.bean;


/**
 * BeanDefinition 的 注册器
 * @author gaozhen
 */
public interface BeanDefinitionRegistry {

	/**
	 * 注册BeanDefinition到bean的容器中
	 * @param beanName
	 * @param beanDefinition
	 */
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

	/**
	 * 获取BeanDefinition的name
	 * @return
	 */
	String[] getBeanDefinitionNames();


	/**
	 * 根据beanDefinitionName获取BeanDefinition
	 * @param beanDefinitionName
	 * @return
	 */
	BeanDefinition getBeanDefinition(String beanDefinitionName);
	
}
