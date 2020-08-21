package com.gz.javastudy.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 
* <p>
* Description:空对象（含义：没有实例属性的对象称之为空对象）
* <p>
* @author gaozhen
* @date 2020年8月21日
* @Version 1.1
 */
public class EmptyObject {
	public static void main(String[] args) {
		EmptyObject emptyObject = new EmptyObject();
		System.out.println(ClassLayout.parseInstance(emptyObject).toPrintable());
	}
}
