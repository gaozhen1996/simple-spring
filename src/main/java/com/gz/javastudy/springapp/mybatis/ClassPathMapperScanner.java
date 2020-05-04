package com.gz.javastudy.springapp.mybatis;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class ClassPathMapperScanner extends ClassPathBeanDefinitionScanner{

	public ClassPathMapperScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	 public Set<BeanDefinition> doScan(String basePackage) {
		 Set<BeanDefinition> beanDefinitions = findCandidateComponents(basePackage);
//		 processBeanDefinitions(beanDefinitions);
		 return beanDefinitions;
	 }
	 
	  /**
	   * Configures parent scanner to search for the right interfaces. It can search
	   * for all interfaces or just for those that extends a markerInterface or/and
	   * those annotated with the annotationClass
	   */
	  public void registerFilters() {
	    boolean acceptAllInterfaces = true;

	    if (acceptAllInterfaces) {
	      // default include filter that accepts all classes
	      addIncludeFilter(new TypeFilter() {
	        @Override
	        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
	          return true;
	        }
	      });
	    }
	  }
	  
	  @Override
	  protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
	    return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
	  }
}
