package com.gz.javastudy.spring.context.annotation;


import com.gz.javastudy.spring.bean.BeanDefinition;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author gaozhen
 * @title: ClassPathScanningCandidateComponentProvider
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2622:07
 */
public class ClassPathScanningCandidateComponentProvider {


    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        //原本的spring对basePackage做过滤处理,我只是做了判空校验
        if (basePackage==null || "".equals(basePackage)){
            return  null;
        }
        return scanCandidateComponents(basePackage);
    }

    private Set<BeanDefinition> scanCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        try{
            Enumeration<URL> resourceUrls = ClassLoader.getSystemResources(basePackage.replaceAll("\\.","/"));
            while (resourceUrls.hasMoreElements()) {
                URL url = resourceUrls.nextElement();
                File dir = new File(url.getFile());
                for (File file:dir.listFiles()) {
                    System.out.println(file);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return candidates;
    }

}
