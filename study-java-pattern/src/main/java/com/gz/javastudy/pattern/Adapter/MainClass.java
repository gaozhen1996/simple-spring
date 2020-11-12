package com.gz.javastudy.pattern.Adapter;

/**
 * 适配器模式（Adapter）：将一个类的接口转换成客户希望的另外一个接口。Adapter 模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * 目标角色：Target
 * 目前已有的功能:Adaptee
 * 适配器：Adapter
 * @author gaozhen
 *
 */
public class MainClass {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.request();
	}
}
