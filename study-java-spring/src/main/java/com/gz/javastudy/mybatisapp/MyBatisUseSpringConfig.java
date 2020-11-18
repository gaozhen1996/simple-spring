package com.gz.javastudy.mybatisapp;

import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class MyBatisUseSpringConfig {
	
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		
		/**
		 * use oracle
		 */
//		driverManagerDataSource.setDriverClassName("oracle.jdbc.OracleDriver");  //加载驱动
//		String url = "jdbc:oracle:thin:@localhost:1521:orcl";  //数据库路径
//		driverManagerDataSource.setUrl(url);
//		driverManagerDataSource.setUsername("FD20180816B");
//		driverManagerDataSource.setPassword("FD20180816B");

		/**
		 * use sqlplie
		 */
		driverManagerDataSource.setDriverClassName("org.sqlite.JDBC");  //加载驱动
		String url = "jdbc:sqlite::resource:mybatis/gz.db";  //数据库路径
		driverManagerDataSource.setUrl(url);
		
		return driverManagerDataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	  	factoryBean.setDataSource(dataSource());
	  	//设置mybatis配置文件路径
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
		factoryBean.setConfigLocation(inputStreamResource);
	  	return factoryBean.getObject();
	}
}
