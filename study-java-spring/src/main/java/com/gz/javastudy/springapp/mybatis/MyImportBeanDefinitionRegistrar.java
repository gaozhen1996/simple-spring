package com.gz.javastudy.springapp.mybatis;

import java.beans.Introspector;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;


/**
 * @author gaozhen
 * @title: MyImportBeanDefinitionRegistrar
 * @projectName study-java
 * @description: TODO
 * @date 2019-11-2923:01
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    	//1.扫描
    	ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
    	scanner.registerFilters();
    	Set<BeanDefinition> bds = scanner.doScan("com.gz.javastudy.mybatisapp.repository");
    	
    	//2.注册
    	for (BeanDefinition beanDefinition : bds) {
    		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanDefinition;
    		String beanClassName = beanDefinition.getBeanClassName();
    		String shortClassName = ClassUtils.getShortName(beanClassName);
    		shortClassName = Introspector.decapitalize(shortClassName);
    		genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
    		genericBeanDefinition.setBeanClass(MapperFactoryBean.class);
    		genericBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
    		registry.registerBeanDefinition(shortClassName,genericBeanDefinition);
    	}
    	
    }
}
