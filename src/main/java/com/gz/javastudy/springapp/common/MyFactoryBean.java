package com.gz.javastudy.springapp.common;

import com.gz.javastudy.springapp.dao.StudentDao;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gaozhen
 * @title: MyFactoryBean
 * @projectName study-java
 * @description: TODO
 * @date 2019-11-3014:29C
 */
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
        System.out.println("proxy");
        return null;
    }
}
