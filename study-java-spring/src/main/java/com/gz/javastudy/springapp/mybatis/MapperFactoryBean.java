package com.gz.javastudy.springapp.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;




/**
 * @author gaozhen
 * @title: MyFactoryBean
 * @projectName study-java
 * @description: TODO
 * @date 2019-11-30
 */
public class MapperFactoryBean<T> extends SqlSessionTemplate implements FactoryBean<T>,InvocationHandler{
	
	Class<T> clazz;
	
    public MapperFactoryBean(Class<T> clazz){
        this.clazz= clazz;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T getObject() throws Exception {
        Class<T>[] clazzs = new Class[]{clazz};
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(),clazzs,this);
        return (T)proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy方法：如果调用接口studentDao的getStudentById方法，模仿执行sql");
        Object result = null;
        String sqlId = clazz.getName()+"."+method.getName();
        Configuration configuration = getConfiguration();
        MappedStatement ms = configuration.getMappedStatement(sqlId);
        if(ms!=null) {
            SqlSessionFactory sessionFactory = getSqlSessionFactory();
            SqlSession session = sessionFactory.openSession();
            result = session.selectOne(sqlId, args[0]);
            session.close();
        }
        
        return result;
    }

}
