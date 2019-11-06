package com.gz.javastudy.spring.context.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gz.javastudy.spring.bean.BeanDefinition;
import com.gz.javastudy.spring.bean.BeanDefinitionRegistry;
import com.gz.javastudy.spring.bean.BeanFactory;
import com.gz.javastudy.spring.bean.factory.BeanDefinitionRegistryPostProcessor;
import com.gz.javastudy.spring.bean.factory.BeanFactoryPostProcessor;


/**
 * AnnotationConfigApplicationContext的后处理器处理的委托。
 * @author gaozhen
 *
 */
public class PostProcessorRegistrationDelegate {
	public static void invokeBeanFactoryPostProcessors(
			BeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {
		if(beanFactory instanceof BeanDefinitionRegistry) {
			BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
			/*
			 * 获取bd注册器后置处理器
			 */
			List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();
			String[] bdNames = registry.getBeanDefinitionNames();
			for (String bdName : bdNames) {
				BeanDefinition bd =registry.getBeanDefinition(bdName);
				//if(bd.getIntrospectedClass() == Application.class) {
				if(bd.getIntrospectedClass() == BeanDefinitionRegistryPostProcessor.class) {
					//System.out.println(beanFactory.getBean(bdName, Application.class));
					currentRegistryProcessors.add(beanFactory.getBean(bdName, BeanDefinitionRegistryPostProcessor.class));
				}
			}
			//执行bd注册器后置处理器
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
		}
	}
	
	private static void invokeBeanDefinitionRegistryPostProcessors(
			Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {

		for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanDefinitionRegistry(registry);
		}
	}	
	
}
