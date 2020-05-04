package com.gz.javastudy.springapp.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author gaozhen
 * @title: MyFactoryBean
 * @projectName study-java
 * @description: TODO
 * @date 2019-11-3014:29C
 */
@SuppressWarnings("rawtypes")
public class MyFactoryBean implements FactoryBean,InvocationHandler{

	Class clazz;

    public MyFactoryBean(Class clazz){
        this.clazz= clazz;
    }

    @Override
    public Object getObject() throws Exception {
        Class[] clazzs = new Class[]{clazz};
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(),clazzs,this);
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy方法：如果调用接口studentDao的getStudentById方法，模仿执行sql");
        System.out.println(clazz.getName()+"."+method.getName());
        if("getStudentById".equals(method.getName())) {
        	System.out.println("select * from student id = "+args[0]);
        }
        return null;
    }
}
