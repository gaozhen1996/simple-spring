package com.gz.javastudy.spring.bean;


import java.util.ArrayList;
import java.util.List;


import com.gz.javastudy.spring.bean.factory.BeanFactoryPostProcessor;
import com.gz.javastudy.spring.context.support.PostProcessorRegistrationDelegate;

/**
 * @author gaozhen
 * @title: AnnotationConfigApplicationContext
 * @projectName springframework
 * @description: TODO
 * @date 2019-09-0109:58
 */
public class AnnotationConfigApplicationContext extends DefaultListableBeanFactory implements BeanDefinitionRegistry{
	
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
	
	public <T> T getBean(String name, Class<T> requiredType) {
		return beanFactory.getBean(name, requiredType);
	}

	public void refresh(){
		/**
		 * spring源码在该处会获取父类的beanFactory，此处模拟为了简化，直接将beanFactory在本类中实例化
		 */
		try {
			invokeBeanFactoryPostProcessors();
			
			// Instantiate all remaining (non-lazy-init) singletons.
			finishBeanFactoryInitialization(beanFactory);	
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	protected void invokeBeanFactoryPostProcessors(){
		PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, beanFactoryPostProcessors);
	}

	protected void finishBeanFactoryInitialization(BeanFactory beanFactory) {
		beanFactory.preInstantiateSingletons();
	}
}
