package com.gz.javastudy.concurrent.thread;
/**
 * 
* <p>
* Description:
* 使用实现Runnable接口的方式来实现多线程
* <p>
* @author gaozhen
* @date 2021年3月13日
* @Version 1.1
 */
public class CreateThreadUseImplRun implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	public static void main(String[] args) {
		new Thread(new CreateThreadUseImplRun()).start();
		new Thread(new CreateThreadUseImplRun()).start();
		new Thread(new CreateThreadUseImplRun()).start();
	}
}
