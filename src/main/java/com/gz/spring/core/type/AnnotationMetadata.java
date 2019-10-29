package com.gz.spring.core.type;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author gaozhen
 * @title: AnnotationMetadata
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2712:14
 */
public class AnnotationMetadata {

    private Set<Annotation> annotations;

    public AnnotationMetadata(Class<?> introspectedClass){
        this.annotations = null;
        annotations = new HashSet<>(Arrays.asList(introspectedClass.getAnnotations()));
    }

    /**
     * 判断是否包含annotationName的注解
     * @param annotationName
     * @return
     */
    public boolean isAnnotated(String annotationName){
        for (Annotation annotation:annotations) {
            System.out.println("com.gz.spring.core.type.AnnotationMetadata:"+annotation);
        }
        if(annotationName.length() > 0 && annotations.contains(annotationName)){
            return true;
        }
        return false;
    }

    public Annotation getAnnotation(String annotationName){
        for (Annotation annotation:annotations) {
            if (annotation.getClass().getName().equals(annotationName)){
                return annotation;
            }
        }
        return null;
    }


}
