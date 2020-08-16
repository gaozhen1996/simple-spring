package com.gz.javastudy.concurrent;

import java.util.concurrent.TimeUnit;

public class TestSynchronized {
	
	static int m = 0;

	public static void main(String[] args){
		int flag = 2;
		switch (flag) {
		case 1:
			/**
			 * 测试synchronized,放在不同的位置，查看字节码的实现原理
			 * 命令：javap -v TestSynchronized (ps:TestSynchronized为class文件名，不需要后缀)
			 */
			final TestSynchronized test = new TestSynchronized();
			for(int i = 0 ; i < 5 ; i++) {
				new Thread(test::accessResources1,"testgz-"+i).start();
			}			
			break;
		case 2:
			/**
			 * 在主线程输出的m小的原因是，主线程在子线程前面执行完成的
			 */
			for(int i = 0 ; i < 10 ; i++) {
				new Thread(()->{
					for(int j = 0;j<5;j++) {
						increase();
					}
					//线程内部打印
					System.out.println(Thread.currentThread().getName()+":M:"+m);
				},"testgz-"+i).start();
			}				
			//主线程打印
			System.out.println("m:"+m);
			
			break;
		default:
			break;
		}

	}
	
	/**
	* @Description: 模拟m++，直接m++看不出效果，中间加一个耗时操作，使其为原子操作
	* @param  参数说明
	* @return void    返回类型
	* @author gaozhen
	 */
	public synchronized static void increase() {
		int t = m;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		t = t+1;
		m = t;
	}
	
	
	/**
	* @Description: synchronized 作用于代码块
	* @param  参数说明
	* @return void    返回类型
	* @author gaozhen
	 */
	public void accessResources1() {
		synchronized (this) {
			try {
				 TimeUnit.MINUTES.sleep(2);
				 System.out.println(Thread.currentThread().getName()+" is running");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	* @Description: synchronized 作用于方法
	* @param  参数说明
	* @return void    返回类型
	* @author gaozhen
	 */
	public synchronized void accessResources2() {
		try {
			 TimeUnit.MINUTES.sleep(2);
			 System.out.println(Thread.currentThread().getName()+" is running");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
