package com.gz.javastudy.springapp.imports;


import com.gz.javastudy.springapp.dao.StudentDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gaozhen
 * @title: StudentDaoAop
 * @projectName study-java
 * @description: TODO
 * @date 2019-12-0320:16
 */
public class StudentDaoAop implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("studentDaoImpl1")){
            bean = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{StudentDao.class}, new MyInvocationHandler(bean));
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
