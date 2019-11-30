package com.gz.javastudy.springapp.common;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * @author gaozhen
 * @title: MapperScan
 * @projectName study-java
 * @description: TODO
 * @date 2019-11-3016:31
 */
@Import(MyImportBeanDefinitionRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperScan {

}
