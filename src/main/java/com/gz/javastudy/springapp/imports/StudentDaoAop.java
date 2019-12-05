package com.gz.javastudy.springapp.imports;


import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.gz.javastudy.springapp.dao.StudentDao;

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
