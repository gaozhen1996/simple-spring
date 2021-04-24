package com.gz.javastudy.sellticket.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {

    private RedissonClient redissonClient;

    public RedissonClient getRedissonClient() {
        synchronized (this) {
            if(redissonClient==null) {
                Config config = new Config();
                config. useSingleServer().setAddress("redis://127.0.0.1:6379");
                RedissonClient redisson = Redisson.create(config);
                this.redissonClient = redisson;
            }
        }
        return this.redissonClient;
    }

}