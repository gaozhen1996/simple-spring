package com.gz.javastudy.springapp.mybatis;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;

public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean, ApplicationListener<ApplicationEvent>{

	private DataSource dataSource;

	private Configuration configuration;
	
	private SqlSessionFactory sqlSessionFactory;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public Resource getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	private Resource configLocation; 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.sqlSessionFactory = buildSqlSessionFactory();
	}

	@Override
	public SqlSessionFactory getObject() throws Exception {
	    if (this.sqlSessionFactory == null) {
	        afterPropertiesSet();
	    }
		return this.sqlSessionFactory;
	}

	@Override
	public Class<?> getObjectType() {
		 return this.sqlSessionFactory == null ? SqlSessionFactory.class : this.sqlSessionFactory.getClass();
	}
	
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		InputStream inputStream =  configLocation.getInputStream();
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		this.sqlSessionFactory = sqlSessionFactory;
		return sqlSessionFactory;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		this.sqlSessionFactory.getConfiguration().getMappedStatementNames();
	}
	
}
