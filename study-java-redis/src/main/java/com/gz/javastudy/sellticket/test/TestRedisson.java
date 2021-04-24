package com.gz.javastudy.sellticket.test;

import com.gz.javastudy.sellticket.App;
import com.gz.javastudy.sellticket.config.RedisConfig;
import com.gz.javastudy.sellticket.coreframe.SpringApplication;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author gaozhen
 * @title: TestRedisson
 * @projectName study-java
 * @description: 测试redisson
 * @date 2021/4/24上午10:54
 */
public class TestRedisson {

    public static void main(String[] args) {
        RedissonClient client = new RedisConfig().getRedissonClient();
        RBucket<String> keyObject = client.getBucket("key");
        keyObject.set("gaozhen");
        System.out.println(keyObject.get());
        System.exit(0);
    }

}
