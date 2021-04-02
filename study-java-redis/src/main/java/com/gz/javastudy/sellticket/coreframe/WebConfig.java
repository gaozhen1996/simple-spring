package com.gz.javastudy.sellticket.coreframe;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		System.out.println("=============配置JSON解析器===============");
		converters.add(new FastJsonHttpMessageConverter());
	}
	
}
