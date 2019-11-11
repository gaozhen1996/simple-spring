package com.gz.javastudy.spring.context.annotation;


import java.beans.Introspector;
import java.io.File;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

import com.gz.javastudy.spring.bean.BeanDefinition;

/**
 * @author gaozhen
 * @title: ClassPathScanningCandidateComponentProvider
 * @projectName simple-spring
 * @description: TODO
 * @date 2019-10-2622:07
 */
public class ClassPathScanningCandidateComponentProvider {
	
	private Set<BeanDefinition> candidates = null;

	/**
	 * 返回的BeanDefinition是加了注解的
	 * @param basePackage
	 * @return
	 */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        //原本的spring对basePackage做过滤处理,我只是做了判空校验
        if (basePackage==null || "".equals(basePackage)){
            return null;
        }
        candidates = new LinkedHashSet<>();
        try{
            scanCandidateComponents(basePackage);
        }catch (Exception e){
            e.printStackTrace();
        }        
        return candidates;
    }

    private void scanCandidateComponents(String basePackage) throws ClassNotFoundException {
    	URL url =  ClassLoader.getSystemResource(basePackage.replaceAll("\\.","/"));
	    File dir = new File(url.getPath());
	    for (File file:dir.listFiles()) {
	    	if(file.isDirectory()) {
	    		scanCandidateComponents(basePackage+"."+file.getName());
	    	}else {
	    		if(file.getName().endsWith(".class")) {
	    			String fullClassName = basePackage + "." + file.getName().replaceAll(".class", "").trim();
	    			Class<?> clazz = Class.forName(fullClassName);
	    			//将class文件转化为BD
	    			AnnotatedBeanDefinition bd = new AnnotatedBeanDefinition(clazz);
	    			String beanName = Introspector.decapitalize(clazz.getSimpleName());
	    			bd.setBeanName(beanName);
	    			if(ConfigurationClassUtils.checkConfigurationClassCandidate(bd)) {
	    				candidates.add(bd);
	    			}
	    		}
	    	}
	    }
    }
    
}
