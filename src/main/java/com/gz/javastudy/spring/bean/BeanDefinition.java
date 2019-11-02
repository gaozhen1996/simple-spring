package com.gz.javastudy.spring.bean;

import java.lang.annotation.Annotation;

public abstract class BeanDefinition {
	
	//懒加载
	private boolean lazyInit = false;
	
	private String scope = "";
	
	private final Class<?> introspectedClass;
	
	private String beanName;
	
	public BeanDefinition(Class<?> introspectedClass) {
		this.introspectedClass = introspectedClass;
	}

	public boolean isLazyInit() {
		return lazyInit;
	}

	public void setLazyInit(boolean lazyInit) {
		this.lazyInit = lazyInit;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Class<?> getIntrospectedClass() {
		return introspectedClass;
	}
	
	public boolean isAnnotationPresent(Class<? extends Annotation> annotation) {
		return this.introspectedClass.isAnnotationPresent(annotation);
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
}
