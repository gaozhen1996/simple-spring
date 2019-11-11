package com.gz.javastudy.spring.context.annotation;

import com.gz.javastudy.spring.bean.BeanDefinition;

public class ScannedGenericBeanDefinition extends BeanDefinition{

	public ScannedGenericBeanDefinition(Class<?> introspectedClass) {
		super(introspectedClass);
	}

}
