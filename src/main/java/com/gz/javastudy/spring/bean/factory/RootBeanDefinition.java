package com.gz.javastudy.spring.bean.factory;


import com.gz.javastudy.spring.bean.BeanDefinition;

/**
 * @author gaozhen
 * @title: RootBeanDefinition
 * @projectName study-java
 * @description: TODO
 * @date 2019-11-1021:39
 */
public class RootBeanDefinition extends BeanDefinition {
    public RootBeanDefinition(Class<?> introspectedClass) {
        super(introspectedClass);
    }
}
