package com.gz.javastudy.javase.reflect;

import java.lang.reflect.Constructor;

/**
 * 
 * @author gaozhen
 * 反射创建对象的三种方式
 * 反射创建对象一定需要这个类有空参的构造方法，否则的话会报java.lang.NoSuchMethodException异常
 */
public class ReflectCreateObjectThreeWay {
	
	static {
		System.out.println("静态代码块");
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		ReflectCreateObjectThreeWay obj = new ReflectCreateObjectThreeWay();
		/**
		 * 反射创建对象都是需要通过Class类来时实现的，第一步是需要创建Class对象
		 */
		//第一种创建Class的方法,通过对象来获取class对象
		Class clazz1 = obj.getClass();
		System.out.println(clazz1.newInstance());
		
		//第二种，通过类名来获取class对象
		Class clazz2 = ReflectCreateObjectThreeWay.class;
		System.out.println(clazz2.newInstance());
	
		//第三种，通过Class.forName获取Class对象
		Class clazz3 = Class.forName("com.gz.javastudy.javase.reflect.ReflectCreateObjectThreeWay");
		System.out.println(clazz3.newInstance());
		
		//这种方式只是加载了类，没有进行初始化
		Class clazz4 = ClassLoader.getSystemClassLoader().loadClass("com.gz.javastudy.javase.reflect.ReflectCreateObjectThreeWay");
		System.out.println(clazz4.newInstance());
		
		@SuppressWarnings("unchecked")
		Constructor<ReflectCreateObjectThreeWay> ctor = clazz1.getDeclaredConstructor();
		System.out.println(ctor.newInstance());
		
	}
	
	
}
