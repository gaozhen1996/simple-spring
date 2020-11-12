package com.gz.javastudy.spring.bean.factory;

import com.gz.javastudy.spring.bean.BeanDefinitionRegistry;

public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor{
	void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry);
}
