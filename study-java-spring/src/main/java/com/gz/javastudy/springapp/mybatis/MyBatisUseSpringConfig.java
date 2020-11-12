package com.gz.javastudy.springapp.mybatis;

import java.io.InputStream;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

@Component
public class MyBatisUseSpringConfig {
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	  	//设置mybatis配置文件路径
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
		factoryBean.setConfigLocation(inputStreamResource);
	  	return factoryBean.getObject();
	}
}
