package com.gz.javastudy.springapp.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateSQLLite {
	
	public static void createStudentTable() {
		try {
			// 加载驱动,连接sqlite的jdbc
			Class.forName("org.sqlite.JDBC");
			// 连接数据库how2j.db,不用手动创建。。。
			Connection connection = DriverManager.getConnection("jdbc:sqlite:gz.db");
			// 创建连接对象，是Java的一个操作数据库的重要接口
			Statement statement = connection.createStatement();
			// 判断是否有表tables的存在。有则删除
			statement.executeUpdate("drop table if exists t_mybatis_student");
			// 创建表
			statement.executeUpdate("create table t_mybatis_student(name varchar(100),sex varchar(2))");
			//插入数据
			statement.executeUpdate("insert into t_mybatis_student values('gz','男')");
			// 搜索数据库，将搜索的放入数据集ResultSet中
//			ResultSet rSet = statement.executeQuery("select * from t_mybatis_student");
//			while (rSet.next()) { // 遍历这个数据集
//				System.out.println("姓名：" + rSet.getString(1));
//				System.out.println("男：" + rSet.getString(2));
//			}
//			rSet.close();// 关闭数据集
			connection.close();// 关闭数据库连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
