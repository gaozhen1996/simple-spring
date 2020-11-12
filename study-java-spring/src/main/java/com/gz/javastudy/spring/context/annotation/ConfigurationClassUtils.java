package com.gz.javastudy.spring.context.annotation;

import com.gz.javastudy.spring.bean.BeanDefinition;
import com.gz.javastudy.spring.context.ComponentScan;
import com.gz.javastudy.spring.core.type.AnnotationMetadata;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gaozhen
 * @title: ConfigurationClassUtils
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2713:16
 */
public class ConfigurationClassUtils {

    private static final Set<String> candidateIndicators = new HashSet<>(8);

    static {
        candidateIndicators.add(ComponentScan.class.getName());
    }

    /**
     * 本方法在spring源码是在ConfigurationClassUtils类的静态方法
     * 判断是不是加了Configuration注解，但是源码里面是还包括加了下面注解的也会返回true
     * 		candidateIndicators.add(Component.class.getName());
     * 		candidateIndicators.add(ComponentScan.class.getName());
     * 		candidateIndicators.add(Import.class.getName());
     * 		candidateIndicators.add(ImportResource.class.getName());
     * @return
     */
    public static boolean checkConfigurationClassCandidate(BeanDefinition beanDefinition){
        AnnotationMetadata metadata = null;
        if(beanDefinition instanceof AnnotatedBeanDefinition){
            metadata = ((AnnotatedBeanDefinition) beanDefinition).getAnnotationMetadata();
        }

        if (metadata == null){
            return false;
        }
        for (String candidateIndicator:candidateIndicators) {
            if (metadata.isAnnotated(candidateIndicator)){
                return true;
            }
        }

        return false;
    }
}
