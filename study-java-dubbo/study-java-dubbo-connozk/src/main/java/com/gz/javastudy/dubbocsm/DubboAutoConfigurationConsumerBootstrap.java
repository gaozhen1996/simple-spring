package com.gz.javastudy.dubbocsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan("com.gz.javastudy.dubbocsm")
public class DubboAutoConfigurationConsumerBootstrap {

    public static void main(String[] args) {
//        SpringApplication.run(DubboAutoConfigurationConsumerBootstrap.class).close();
        SpringApplication.run(DubboAutoConfigurationConsumerBootstrap.class);
        
    }


}