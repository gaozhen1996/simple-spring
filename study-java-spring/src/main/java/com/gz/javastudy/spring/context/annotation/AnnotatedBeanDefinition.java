package com.gz.javastudy.spring.context.annotation;

import com.gz.javastudy.spring.bean.BeanDefinition;
import com.gz.javastudy.spring.context.Scope;
import com.gz.javastudy.spring.core.type.AnnotationMetadata;


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
        //解析注解的值,放入bd
        //1.Scope
        Scope scope = (Scope) annotationMetadata.getAnnotation(Scope.class.getName());
        this.setScope(scope==null?"singleton":scope.value());
    }

    public final AnnotationMetadata getAnnotationMetadata(){
        return this.annotationMetadata;
    }
}
