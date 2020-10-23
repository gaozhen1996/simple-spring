package com.gz.javastudy.spring.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.lang.Nullable;

import com.gz.javastudy.spring.bean.factory.DefaultSingletonBeanRegistry;
import com.gz.javastudy.spring.bean.factory.RootBeanDefinition;


public class DefaultListableBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry{

	//存beanDefinition,key是beanName
	public final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

	private final Map<String,Object> stringObjectMap = new ConcurrentHashMap<>(256);

	//存放beanName的list
	private final List<String> beanDefinitionNames  = new ArrayList<>(64);

	@Override
	public <T> T getBean(String name, Class<T> requiredType) {
		return doGetBean(name, null, null, false);
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


	public Object createBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args){
		Object bean = null;
		Class<?>  introspectedClass = mbd.getIntrospectedClass();
		try {
			synchronized (mbd.lock){
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
	
	@SuppressWarnings("unchecked")
	protected <T> T doGetBean(final String name, final Class<T> requiredType,
			 final Object[] args, boolean typeCheckOnly) {
		String beanName = name;
		Object bean = null;

		// Eagerly check singleton cache for manually registered singletons.
		Object sharedInstance = getSingleton(beanName);
		if (sharedInstance != null && args == null) {
			
		}else {
			// 解决依赖关系，将依赖的bean提前实例化,未完成
			final RootBeanDefinition mbd = (RootBeanDefinition) this.beanDefinitionMap.get(beanName);
			
			/**
			 * 1.实例化单例的bean
			 * 2.实例化原型的bean
			 */
			//1.实例化单例的bean
			if(mbd.getScope().equals("singleton")) {
				//先尝试从缓存中获取
				bean = stringObjectMap.get(name);
				//缓存中没找到，则需要创建
				if(bean==null){
					createBean(beanName, mbd, args);
				}
			}
			//2.实例化原型的bean
			else if(mbd.getScope().equals("protype")) {
				createBean(beanName, mbd, args);
			}
		}
		return (T) bean;
	}
}
