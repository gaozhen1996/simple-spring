package com.gz.javastudy.spring.context.annotation;


import com.gz.javastudy.spring.bean.BeanDefinition;

import java.util.Set;

/**
 * @author gaozhen
 * @title: ClassPathBeanDefinitionScanner
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2622:37
 */
public class ClassPathBeanDefinitionScanner extends  ClassPathScanningCandidateComponentProvider {

    /**
     * 原本的spring方法返回值是Set<BeanDefinitionHolder>
     * @param basePackages
     * @return
     */
    protected Set<BeanDefinition> doScan(String... basePackages) {
        Set<BeanDefinition> candidates = null;
        for (String basePackage:basePackages) {
            candidates = findCandidateComponents(basePackage);
        }

        return candidates;
    }
}
