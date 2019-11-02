package com.gz.javastudy.springsimple.demo.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;


@Service
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
