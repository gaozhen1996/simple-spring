package com.gz.javastudy.spring.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gz.javastudy.spring.bean.factory.DefaultSingletonBeanRegistry;
import com.gz.javastudy.spring.bean.factory.RootBeanDefinition;


public class DefaultListableBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry{

	//存beanDefinition,key是beanName
	public final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

	private final Map<String,Object> stringObjectMap = new ConcurrentHashMap<>(256);

	//存放beanName的list
	private final List<String> beanDefinitionNames  = new ArrayList<>(64);

	@SuppressWarnings("unchecked")
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

	@Override
	public void preInstantiateSingletons() {
		List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
		for (String beanName : beanNames) {
			BeanDefinition bd = this.beanDefinitionMap.get(beanName);
			getBean(beanName);
		}	
		
	}


	@Override
	public Object getBean(String name) {
		return doGetBean(name, null, null, false);
	}
	
	protected <T> T doGetBean(final String name, final Class<T> requiredType,
			 final Object[] args, boolean typeCheckOnly) {
		String beanName = name;
		Object bean = null;

		// Eagerly check singleton cache for manually registered singletons.
		Object sharedInstance = getSingleton(beanName);
		if (sharedInstance != null && args == null) {
			
		}else {
			// 解决依赖关系，将依赖的bean提前实例化 ,未完成
			final RootBeanDefinition mbd = (RootBeanDefinition) this.beanDefinitionMap.get(beanName);
			
		}
		
		return (T) bean;
	}
}
