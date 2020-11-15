package com.gz.javastudy.dubbocsm;


import org.apache.dubbo.config.annotation.DubboReference;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.gz.javastudy.dubboapi.DemoService;

/**
 * Dubbo Registry ZooKeeper Consumer Bootstrap
 */
@EnableAutoConfiguration
public class DubboRegistryZooKeeperConsumerBootstrap {


    @DubboReference(version = "${demo.service.version}")
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(DubboRegistryZooKeeperConsumerBootstrap.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> System.out.println(demoService.add(1,2));
    }
}