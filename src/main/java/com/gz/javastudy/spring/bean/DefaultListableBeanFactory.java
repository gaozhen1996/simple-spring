package com.gz.javastudy.spring.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DefaultListableBeanFactory implements BeanFactory, BeanDefinitionRegistry{

	//存beanDefinition,key是beanName
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

	private final Map<String,Object> stringObjectMap = new ConcurrentHashMap<>(156);

	//存放beanName的list
	private final List<String> beanDefinitionNames  = new ArrayList<>(64);

	@Override
	public <T> T getBean(String name, Class<T> requiredType) {
		Object instanceObject = null;
		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
		if (beanDefinition.getScope().equals("singleton")){
			//先尝试从缓存中获取
			instanceObject = stringObjectMap.get(name);
			//缓存中没找到，则需要创建
			if(instanceObject==null){
				instanceObject = createBean(beanDefinition);
				stringObjectMap.put(name,instanceObject);
			}
		}else{
			//如果不是单例的，需要重新创建bean
			instanceObject = createBean(beanDefinition);
		}
		Class<?> introspectedClass = beanDefinition.getIntrospectedClass();

		return (T)instanceObject;
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


	public Object createBean(BeanDefinition beanDefinition){
		Object bean = null;
		Class<?>  introspectedClass = beanDefinition.getIntrospectedClass();
		try {
			synchronized (beanDefinition.lock){
				bean = introspectedClass.newInstance();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return bean;
	}

}
