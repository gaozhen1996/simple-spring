package com.gz.javastudy.netty.spring;

import com.gz.javastudy.netty.server.NettyServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
              new Thread(){
                  @Override
                  public void run() {
                      NettyServer.start();
                  }
              }.start();
    }
}
