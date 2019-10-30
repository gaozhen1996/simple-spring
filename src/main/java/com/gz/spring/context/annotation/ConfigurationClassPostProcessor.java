package com.gz.spring.context.annotation;


import com.gz.spring.bean.BeanDefinition;
import com.gz.spring.bean.BeanDefinitionRegistry;
import com.gz.spring.context.ComponentScan;
import com.gz.spring.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gaozhen
 * @title: ConfigurationClassPostProcessor
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2710:51
 */
public class ConfigurationClassPostProcessor {

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        processConfigBeanDefinitions(registry);
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
    public final void doProcessConfigurationClass(AnnotatedBeanDefinition beanDefinition){
        //用于扫描包
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner();

        //1.解析ComponentScan
        AnnotationMetadata annotationMetadata = beanDefinition.getAnnotationMetadata();
        if(annotationMetadata.isAnnotated(ComponentScan.class.getName())){
            ComponentScan annotation = (ComponentScan) annotationMetadata.getAnnotation(ComponentScan.class.getName());
            String[] packages  = annotation.value();
            for (String needScanPackage:packages) {
                scanner.doScan(needScanPackage);
            }

        }

    }

}
