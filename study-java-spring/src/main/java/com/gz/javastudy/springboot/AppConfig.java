package com.gz.javastudy.springboot;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
@ComponentScan("com.gz.javastudy.springboot")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer{
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		System.out.println("=============配置JSON解析器===============");
		converters.add(new FastJsonHttpMessageConverter());
	}
	
}
