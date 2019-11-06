package com.gz.javastudy.spring.bean.factory;

import com.gz.javastudy.spring.bean.BeanFactory;

/**
 * 原本在spring中，这个类是放在bean.factory.config包中
 * @author gaozhen
 *
 */
public interface BeanFactoryPostProcessor {
	void postProcessBeanFactory(BeanFactory beanFactory);
}
