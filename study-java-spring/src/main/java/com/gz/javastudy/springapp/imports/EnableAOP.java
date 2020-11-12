package com.gz.javastudy.springapp.imports;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author gaozhen
 * @title: EanbleAOP
 * @projectName study-java
 * @description: TODO
 * @date 2019-12-0320:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportSelector.class)
public @interface EnableAOP {

}
