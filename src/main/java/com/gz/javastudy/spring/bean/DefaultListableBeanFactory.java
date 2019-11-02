package com.gz.javastudy.spring.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DefaultListableBeanFactory implements BeanFactory, BeanDefinitionRegistry{

	//存beanDefinition,key是beanName
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

	private final List<String> beanDefinitionNames  = new ArrayList<>(64);

	@Override
	public <T> T getBean(String name, Class<T> requiredType) {
		return null;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		beanDefinitionNames.add(beanName);
		beanDefinitionMap.put(beanName, beanDefinition);
	}

	@Override
	public String[] getBeanDefinitionNames() {
		String[] bds = new String[beanDefinitionNames.size()];
		return beanDefinitionNames.toArray(bds);
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanDefinitionName) {
		return beanDefinitionMap.get(beanDefinitionName);
	}

}
