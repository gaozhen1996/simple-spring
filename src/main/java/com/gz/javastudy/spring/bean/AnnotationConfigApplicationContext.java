package com.gz.javastudy.spring.bean;


import java.util.ArrayList;
import java.util.List;


import com.gz.javastudy.spring.bean.factory.BeanFactoryPostProcessor;
import com.gz.javastudy.spring.context.annotation.ConfigurationClassPostProcessor;
import com.gz.javastudy.spring.context.support.PostProcessorRegistrationDelegate;

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
	
	private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();
	
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
	
	public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) {
		this.beanFactoryPostProcessors.add(postProcessor);
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
		PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, beanFactoryPostProcessors);
	}

}
