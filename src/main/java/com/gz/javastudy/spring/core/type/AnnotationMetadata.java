package com.gz.javastudy.spring.core.type;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author gaozhen
 * @title: AnnotationMetadata
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2712:14
 */
public class AnnotationMetadata {

    private Map<String,Annotation> annotations = new HashMap<String, Annotation>();

    public AnnotationMetadata(Class<?> introspectedClass){
        for (Annotation annotation : introspectedClass.getAnnotations()) {
			annotations.put(annotation.annotationType().getName(), annotation);
		}
    }

    /**
     * 判断是否包含annotationName的注解
     * @param annotationName
     * @return
     */
    public boolean isAnnotated(String annotationName){
        if(annotationName.length() > 0 && annotations.containsKey(annotationName)){
            return true;
        }
        return false;
    }

    public Annotation getAnnotation(String annotationName){
        if(isAnnotated(annotationName)) {
        	return annotations.get(annotationName);
        }
        return null;
    }


}
