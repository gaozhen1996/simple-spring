package com.gz.javastudy.spring.context.annotation;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gz.javastudy.spring.bean.BeanDefinition;
import com.gz.javastudy.spring.bean.BeanDefinitionRegistry;
import com.gz.javastudy.spring.bean.BeanFactory;
import com.gz.javastudy.spring.bean.factory.BeanDefinitionRegistryPostProcessor;
import com.gz.javastudy.spring.context.ComponentScan;
import com.gz.javastudy.spring.context.Service;
import com.gz.javastudy.spring.core.type.AnnotationMetadata;
import com.gz.javastudy.spring.core.type.filter.TypeFilter;

/**
 * @author gaozhen
 * @title: ConfigurationClassPostProcessor
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2710:51
 */
public class ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor{

	private ClassPathBeanDefinitionScanner scanner;
	
	@Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        processConfigBeanDefinitions(registry);
    }

	@Override
	public void postProcessBeanFactory(BeanFactory beanFactory) {
		//空实现
	}
	
    /**
     * 解析加了Configuration注解的类
     * @param registry
     */
    public void processConfigBeanDefinitions(BeanDefinitionRegistry registry) {
        List<AnnotatedBeanDefinition> configCandidates = new ArrayList<>();
        //1.获取BeanFactory中所有的BeanDefinition
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String beanDefinitionName:beanDefinitionNames) {
            //2.判断是不是加了Configuration注解
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            if(ConfigurationClassUtils.checkConfigurationClassCandidate(beanDefinition)){
                configCandidates.add((AnnotatedBeanDefinition) beanDefinition);
            }
        }
        
        /**
         * 	在spring源码中，这个类的实例化是在
         * org.springframework.context.annotation.ComponentScanAnnotationParser
         * .parse(AnnotationAttributes, String)完成的，此处简化了
         */
        scanner = new ClassPathBeanDefinitionScanner(registry);
        //3.扫描包
        for (AnnotatedBeanDefinition annotatedBeanDefinition:configCandidates) {
            doProcessConfigurationClass(annotatedBeanDefinition);
        }
    }


    /**
     * 该方法在spring源码中是org.springframework.context.annotation.ConfigurationClassParser的方法,
     * 为了减少类的数量，因此写到这个类中
     * @param beanDefinition
     */
    private final void doProcessConfigurationClass(AnnotatedBeanDefinition beanDefinition){
        //1.解析ComponentScan
        AnnotationMetadata annotationMetadata = beanDefinition.getAnnotationMetadata();
        if(annotationMetadata.isAnnotated(ComponentScan.class.getName())){
            ComponentScan annotation = (ComponentScan) annotationMetadata.getAnnotation(ComponentScan.class.getName());
            String[] packages  = annotation.value();
            for (String needScanPackage:packages) {
            	//添加不需要注册的class
            	scanner.addExcludeFilter(new TypeFilter() {
					@Override
					public boolean matchClassName(String className) {
						return beanDefinition.getIntrospectedClass().getName().equals(className);
					}
				});
            	//添加需要注册到容器中的注解
            	scanner.addCludeAnnotation(ComponentScan.class.getName()
            			                  ,Service.class.getName());
            	//扫描
            	Set<BeanDefinition> scannedBeanDefinitions = scanner.doScan(needScanPackage);
            	//Check the set of scanned definitions for any further config classes and parse recursively if needed
            	for (BeanDefinition scanBeanDefinition : scannedBeanDefinitions) {
            		if(ConfigurationClassUtils.checkConfigurationClassCandidate(scanBeanDefinition)){
            			AnnotatedBeanDefinition sacnConfigurationbd =  (AnnotatedBeanDefinition) scanBeanDefinition;
            			doProcessConfigurationClass(sacnConfigurationbd);
            		}
				}
            	
            }
        }

    }

}
