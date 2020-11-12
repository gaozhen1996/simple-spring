package com.gz.javastudy.spring.context.annotation;


import com.gz.javastudy.spring.bean.BeanDefinition;
import com.gz.javastudy.spring.bean.BeanDefinitionRegistry;
import com.gz.javastudy.spring.bean.factory.RootBeanDefinition;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author gaozhen
 * @title: AnnotationConfigUtils
 * @projectName study-java
 * @description: TODO
 * @date 2019-11-10 21:32
 */
public class AnnotationConfigUtils {
    public static Set<BeanDefinition> registerAnnotationConfigProcessors(BeanDefinitionRegistry registry){
        Set<BeanDefinition> beanDefs = new LinkedHashSet<>(8);
        //1.ConfigurationClassPostProcessor
        BeanDefinition def = new RootBeanDefinition(ConfigurationClassPostProcessor.class);
        def.setBeanName(ConfigurationClassPostProcessor.class.getName());
        registry.registerBeanDefinition(def.getBeanName(),def);
        beanDefs.add(def);
        return beanDefs;
    }

}
