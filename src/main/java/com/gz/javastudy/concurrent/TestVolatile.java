package com.gz.javastudy.concurrent;

import java.util.concurrent.TimeUnit;

public class TestVolatile {
	static int MAX = 10;
	
	static volatile int init_value = 0;
	
	public static void main(String[] args) {
		new Thread(()->{
			int localvalue = init_value;
			while(localvalue<MAX) {
				if(localvalue<MAX) {
					if(localvalue != init_value) {
						System.out.println("read:"+init_value);	
					}
					localvalue = init_value;
				}
			}
		},"Read").start();
		
		new Thread(()->{
			int localValue = init_value;
			while(localValue<MAX) {
				System.out.println("write "+(++localValue));
				init_value = localValue;
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"Write").start();
	}
	

}
