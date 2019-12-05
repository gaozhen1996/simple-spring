package com.gz.javastudy.springapp.imports;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author gaozhen
 * @title: MyInvocationHandler
 * @projectName study-java
 * @description: TODO
 * @date 2019-12-03 20:21
 */
public class MyInvocationHandler implements InvocationHandler {

    private  Object target;

    public MyInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	if("getStudentById".equals(method.getName())) {
    		 System.out.println("代理方法执行:模拟AOP方法");
    	}
        return method.invoke(this.target,args);
    }
}
