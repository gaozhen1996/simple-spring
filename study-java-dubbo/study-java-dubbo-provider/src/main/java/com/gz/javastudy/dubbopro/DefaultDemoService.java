package com.gz.javastudy.dubbopro;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

import com.gz.javastudy.dubboapi.DemoService;

@SuppressWarnings("deprecation")
@Service(version = "1.0.0")
public class DefaultDemoService implements DemoService {

    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;

    public String sayHello(String name) {
        return String.format("[%s] : Hello,固守青城, %s", serviceName, name);
    }
}
