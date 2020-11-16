package com.gz.javastudy.dubbocsm;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.gz.javastudy.dubboapi.DemoService;


@SuppressWarnings("deprecation")
@EnableAutoConfiguration
public class DubboAutoConfigurationConsumerBootstrap {

    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(DubboAutoConfigurationConsumerBootstrap.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> System.out.println(demoService.add(1,2));
    }
}