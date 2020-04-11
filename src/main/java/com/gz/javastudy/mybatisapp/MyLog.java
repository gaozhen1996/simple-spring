package com.gz.javastudy.mybatisapp;

import java.util.logging.Logger;

/**
 * 测试实现自己的log实现
 * @author gaozhen
 *
 */
public class MyLog implements org.apache.ibatis.logging.Log{
	
	Logger log = Logger.getLogger("myLog");

	public MyLog() {
		super();
	}	
	
	public MyLog(String clazz) {
		super();
	}

	@Override
	public boolean isDebugEnabled() {
		return true;
	}

	@Override
	public boolean isTraceEnabled() {
		return true;
	}

	@Override
	public void error(String s, Throwable e) {
		log.info(s);
		
	}

	@Override
	public void error(String s) {
		log.info(s);
		
	}

	@Override
	public void debug(String s) {
		log.info(s);
	}

	@Override
	public void trace(String s) {
		log.info(s);
		
	}

	@Override
	public void warn(String s) {
		log.info(s);
		
	}

}
