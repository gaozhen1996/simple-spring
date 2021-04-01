package com.gz.javastudy.sellticket;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

@Component
public class Config {
	
	private SqlSession sqlSession;
	
	@PostConstruct
	public SqlSession getSqlSession() {
		if (this.sqlSession == null) {
			System.out.println("=======================加载SqlSession======================================");
			String resource = "mybatis/mybatis-config.xml";
			InputStream inputStream = null;
			try {
				inputStream = Resources.getResourceAsStream(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			this.sqlSession = sqlSessionFactory.openSession();
		}
		
		return this.sqlSession;
	}
}
