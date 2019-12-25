package com.gz.javastudy.javase.reflect;

public class ClassforNameORClassLoader {
	
	public static void main(String[] args) throws Exception {
		String className = "com.gz.javastudy.javase.reflect.ReflectCreateObjectThreeWay";
		ClassLoader.getSystemClassLoader().loadClass(className);
		/**
		 * 如果注释这样代码将不会输出“静态代码块”
		 */
		//Class.forName(className);
	}
}
