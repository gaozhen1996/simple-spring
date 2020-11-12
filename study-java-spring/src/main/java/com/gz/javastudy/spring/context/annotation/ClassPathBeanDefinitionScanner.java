package com.gz.javastudy.spring.context.annotation;


import com.gz.javastudy.spring.bean.BeanDefinition;
import com.gz.javastudy.spring.bean.BeanDefinitionRegistry;

import java.util.Set;



/**
 * @author gaozhen
 * @title: ClassPathBeanDefinitionScanner
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2622:37
 */
public class ClassPathBeanDefinitionScanner extends  ClassPathScanningCandidateComponentProvider {

	private final BeanDefinitionRegistry registry;
	
	public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
	
    /**
     * 	原本的spring方法返回值是Set<BeanDefinitionHolder>
     * @param basePackages
     * @return
     */
    protected Set<BeanDefinition> doScan(String... basePackages) {
        Set<BeanDefinition> candidates = null;
        for (String basePackage:basePackages) {
        	//扫描包，获取BeanDefinition对象
            candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
				if(checkCandidate(candidate.getBeanName(),candidate)) {
					registerBeanDefinition(candidate, registry);
				}
			}
        }

        return candidates;
    }
    
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) {
    	if(registry.getBeanDefinition(beanName)==null) {
    		return true;
    	}
    	
    	if(beanDefinition!=null) {
    		@SuppressWarnings("unused")
			BeanDefinition existingDef = registry.getBeanDefinition(beanName);
    		existingDef = beanDefinition;
    	}
    	return false;
    }
    
	protected void registerBeanDefinition(BeanDefinition beanDefinition, BeanDefinitionRegistry registry) {
		this.registry.registerBeanDefinition(beanDefinition.getBeanName(), beanDefinition);
	}
}
