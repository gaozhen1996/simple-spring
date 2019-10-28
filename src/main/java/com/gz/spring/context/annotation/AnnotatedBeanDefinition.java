package com.gz.spring.context.annotation;

import com.gz.spring.bean.BeanDefinition;
import com.gz.spring.core.type.AnnotationMetadata;

/**
 * @author gaozhen
 * @title: AnnotatedBeanDefinition
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2712:13
 */
public class AnnotatedBeanDefinition extends BeanDefinition {
    //注解元数据
    private final AnnotationMetadata annotationMetadata;

    public AnnotatedBeanDefinition(Class<?> introspectedClass){
        super(introspectedClass);
        annotationMetadata = new AnnotationMetadata(introspectedClass);
    }

    public final AnnotationMetadata getAnnotationMetadata(){
        return this.annotationMetadata;
    }
}
