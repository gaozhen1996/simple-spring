package com.gz.javastudy.javase.reflect;

public class ClassforNameORClassLoader {
	
	public static void main(String[] args) throws Exception {
		String className = "com.gz.javastudy.javase.reflect.ReflectCreateObjectThreeWay";
		//会执行static代码块
//		Class.forName(className);
		//不会执行static代码款
		ClassLoader.getSystemClassLoader().loadClass(className);
		/**
		 * 如果注释这样代码将不会输出“静态代码块”
		 */
		
		/**
		 *  参数一：className，需要加载的类的名称。
         *	参数二：true，是否对class进行初始化（需要initialize）
         *  参数三：classLoader，对应的类加载器
		 */
		//Class.forName(className, false, ClassLoader.getSystemClassLoader());
	}
}
