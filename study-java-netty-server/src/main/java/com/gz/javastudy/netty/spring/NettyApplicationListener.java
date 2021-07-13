package com.gz.javastudy.netty.spring;

import com.gz.javastudy.netty.server.NettyServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class NettyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = Logger.getLogger(NettyApplicationListener.class.getName());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> NettyServer.start()).start();
        logger.info("netty 监听事件完成");
    }
}
