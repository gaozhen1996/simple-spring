package com.gz.javastudy.spring.context;

import java.lang.annotation.*;

/**
 * @author gaozhen
 * @title: Scope
 * @projectName study-java
 * @description: TODO
 * @date 2019-11-1011:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Scope {
    String value() default "";
}
