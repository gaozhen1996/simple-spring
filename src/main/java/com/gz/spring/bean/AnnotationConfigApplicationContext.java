package com.gz.spring.bean;


/**
 * @author gaozhen
 * @title: AnnotationConfigApplicationContext
 * @projectName springframework
 * @description: TODO
 * @date 2019-09-0109:58
 */
public class AnnotationConfigApplicationContext implements BeanDefinitionRegistry{
	
	private final AnnotatedBeanDefinitionReader reader;
	
	private final BeanFactory beanFactory;
	
	public AnnotationConfigApplicationContext() {
		this.beanFactory = new DefaultListableBeanFactory();
		this.reader = new AnnotatedBeanDefinitionReader(this);
	}
	
	public AnnotationConfigApplicationContext(Class<?>... annotatedClass) {
		this();
		this.reader.register(annotatedClass);
	}
	


	/**
	 * 将bean注册到beanFactroy中
	 */
	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
	}


	
}
