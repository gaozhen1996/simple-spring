package com.gz.javastudy.spring.bean;

import com.gz.javastudy.spring.context.annotation.AnnotatedBeanDefinition;
import com.gz.javastudy.spring.context.annotation.AnnotationConfigUtils;

import java.beans.Introspector;

/**
 * BeanDefinition 的 读取器
 * @author gaozhen
 */
public class AnnotatedBeanDefinitionReader {

	private BeanDefinitionRegistry registry;
	
	public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
		AnnotationConfigUtils.registerAnnotationConfigProcessors(registry);
	}
	

	public void register(Class<?>... annotatedClasses) {
		for (Class<?> annotatedClass : annotatedClasses) {
			doRegisterBean(annotatedClass);
		}
	}
	
	/**
	 * 1.将Class转化为BeanDefinition对象
	 * 2.通过注册器注册到BeanFactory中
	 * @param annotatedClass
	 */
	<T> void doRegisterBean(Class<T> annotatedClass) {
		/**
		 * 1.将Class转化为BeanDefinition对象
		 */
		BeanDefinition beanDefinition = new AnnotatedBeanDefinition(annotatedClass);
		//设置默认的beanName
		String beanName = Introspector.decapitalize(annotatedClass.getSimpleName());
		beanDefinition.setBeanName(beanName);
		//
		/**
		 * 2.通过注册器注册到BeanFactory中
		 */
		this.registry.registerBeanDefinition(beanName, beanDefinition);
	}
	
}
