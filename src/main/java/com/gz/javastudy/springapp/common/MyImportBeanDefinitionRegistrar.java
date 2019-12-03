package com.gz.javastudy.springapp.common;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.gz.javastudy.springapp.dao.StudentDao;

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

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(StudentDao.class);
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) builder.getBeanDefinition();
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.gz.javastudy.springapp.dao.StudentDao");
        beanDefinition.setBeanClass(MyFactoryBean.class);
        registry.registerBeanDefinition("studentDao",beanDefinition);
    }
}
