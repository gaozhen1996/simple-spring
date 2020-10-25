package com.gz.javastudy.spring.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import com.gz.javastudy.spring.bean.factory.DefaultSingletonBeanRegistry;


public class DefaultListableBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry{

	//存beanDefinition,key是beanName
	public final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

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

	public Object createBean(String beanName, BeanDefinition mbd,Object[] args){
		return doCreateBean(beanName, mbd, args);
	}
	
	public Object doCreateBean(String beanName, BeanDefinition mbd,Object[] args){
		Object bean = null;
		// Instantiate the bean.
		//spring源码中是在createBeanInstance()方法中来实例化的
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
		
		// Allow post-processors to modify the merged bean definition.
		// 暂不实现
		
		// Eagerly cache singletons to be able to resolve circular references
		// even when triggered by lifecycle interfaces like BeanFactoryAware.
		// 暂不实现
		
		// Initialize the bean instance.
		// 解决循环依赖
		//populateBean(beanName, mbd, instanceWrapper);
		
		return bean;
	}


	
	@Override
	public void preInstantiateSingletons() {
		List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
		//实例化非懒加载的bean
		for (String beanName : beanNames) {
			System.out.println("Instantiate:"+beanName);
			BeanDefinition bd = this.beanDefinitionMap.get(beanName);
			//实际上还需要判断是不是抽象类，此处为了简化，没有实现
			if(!bd.isLazyInit() && bd.getScope().equals("singleton")) {
				if(isFactoryBean(beanName)) {

				}else {
					getBean(beanName);	
				}
			}
		}	
		
	}
	public boolean isFactoryBean(String name) {
		//未实现
		return false;
	}

	@Override
	public Object getBean(String name) {
		return doGetBean(name, null, null, false);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T doGetBean(final String name, final Class<T> requiredType,
			 final Object[] args, boolean typeCheckOnly) {
		String beanName = name;

		// Eagerly check singleton cache for manually registered singletons.
		Object sharedInstance = getSingleton(beanName);
		if (sharedInstance != null && args == null) {
			
		}else {
			final BeanDefinition mbd = this.beanDefinitionMap.get(beanName);
			//解决依赖关系，将依赖的bean提前实例化,未完成
			
			/**
			 * 1.实例化单例的bean
			 * 2.实例化原型的bean
			 */
			//1.实例化单例的bean
			if(mbd.getScope().equals("singleton")) {
				sharedInstance = getSingleton(beanName, () -> {
					return createBean(beanName, mbd, args);
				});
			}
			//2.实例化原型的bean
			else if(mbd.getScope().equals("prototype")) {
				sharedInstance = createBean(beanName, mbd, args);
			}
		}
		return (T) sharedInstance;
	}
}
