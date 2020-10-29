package com.gz.javastudy.springsimple;

import javax.annotation.PostConstruct;

import com.gz.javastudy.springsimple.framework.MyService;



@MyService
public class TestService {

    public TestService (){
        System.out.println("构造方法");
    }

    @PostConstruct
    public void init(){
        System.out.println("init");
    }

    public void query(){
        System.out.println("query");
    }

}
