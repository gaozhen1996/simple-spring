package com.gz.mybatisapp;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gz.mybatisapp.repository.StudentDao;


public class TestMybatis {
	public static void main(String[] args) throws IOException {
    	int flag  = 1;
    	switch (flag) {
		case 1:
			//======================================测试后单独的mybatis========================================
			testSingleMybatis();
			break;
		}
	}
	
	/**
	 * 测试后单独的mybatis
	 * @throws IOException
	 */
	public static void testSingleMybatis() throws IOException {
		System.out.println("======================================测试后单独的mybatis==========================================");
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		StudentDao studentDao = session.getMapper(StudentDao.class);
		/**
		 * 查询两次，sql语句也只会执行一次。因为mybatis默认打开一级缓存 
		 */
		System.out.println(studentDao.selectStudent("gz"));
		System.out.println(studentDao.selectStudent("gz"));
	} 
}
