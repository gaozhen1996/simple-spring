package com.gz.javastudy.spring.bean.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.lang.Nullable;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
	/** Cache of singleton objects: bean name to bean instance. */
	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
	
	/** Cache of early singleton objects: bean name to bean instance. */
	private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
	
	private final Set<String> singletonsCurrentlyInCreation =
			Collections.newSetFromMap(new ConcurrentHashMap<>(16));
	
	@Nullable
	protected Object getSingleton(String beanName, boolean allowEarlyReference) {
		Object singletonObject = this.singletonObjects.get(beanName);
		if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
			synchronized (this.singletonObjects) {
				singletonObject = this.earlySingletonObjects.get(beanName);
//				if (singletonObject == null && allowEarlyReference) {
//					ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
//					if (singletonFactory != null) {
//						singletonObject = singletonFactory.getObject();
//						this.earlySingletonObjects.put(beanName, singletonObject);
//						this.singletonFactories.remove(beanName);
//					}
//				}
			}
		}
		return singletonObject;
	}
	
	public Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
		Object singletonObject = null;
		synchronized (this.singletonObjects) {
			singletonObject = this.singletonObjects.get(beanName);
			if(singletonObject == null) {
				boolean newSingleton = false;
				singletonObject = singletonFactory.getObject();
				newSingleton = true;
				if(newSingleton) {
					singletonObjects.put(beanName,singletonObject);
				}
			}
		}
		return singletonObject;
	}
	
	public boolean isSingletonCurrentlyInCreation(String beanName) {
		return this.singletonsCurrentlyInCreation.contains(beanName);
	}

	@Override
	public Object getSingleton(String beanName) {
		return getSingleton(beanName, true);
	}
}
