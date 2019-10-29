package com.gz.spring.bean;


import com.gz.spring.context.annotation.ConfigurationClassPostProcessor;

/**
 * @author gaozhen
 * @title: AnnotationConfigApplicationContext
 * @projectName springframework
 * @description: TODO
 * @date 2019-09-0109:58
 */
public class AnnotationConfigApplicationContext implements BeanDefinitionRegistry{
	
	private final AnnotatedBeanDefinitionReader reader;
	
	private final DefaultListableBeanFactory beanFactory;
	
	public AnnotationConfigApplicationContext() {
		this.beanFactory = new DefaultListableBeanFactory();
		this.reader = new AnnotatedBeanDefinitionReader(this);
	}
	
	public AnnotationConfigApplicationContext(Class<?>... annotatedClass) {
		this();
		this.reader.register(annotatedClass);
		refresh();
	}
	
	/**
	 * 将bean注册到beanFactroy中
	 */
	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
	}

	@Override
	public String[] getBeanDefinitionNames() {
		return beanFactory.getBeanDefinitionNames();
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanDefinitionName) {
		return beanFactory.getBeanDefinition(beanDefinitionName);
	}

	public void refresh(){
		invokeBeanFactoryPostProcessors();
	}


	protected void invokeBeanFactoryPostProcessors(){
		ConfigurationClassPostProcessor configurationClassPostProcessor = new ConfigurationClassPostProcessor();
		configurationClassPostProcessor.postProcessBeanDefinitionRegistry(this);
	}

}
