package com.gz.javastudy.pattern.Adapter;

/**
 * 类适配器
 * @author gaozhen
 *
 */
public class Adapter extends Adaptee implements Target{

	@Override
	public void request() {
		nowRequest();
	}
}
