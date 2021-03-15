package com.gz.javastudy.concurrent.thread;

/**
 * 
* <p>
* Description:使用继承Thread来实现线程
* <p>
* @author gaozhen
* @date 2021年3月13日
* @Version 1.1
 */
public class CreateThreadUseExtends extends Thread{

	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	public static void main(String[] args) {
		CreateThreadUseExtends mThread1=new CreateThreadUseExtends();
		CreateThreadUseExtends mThread2=new CreateThreadUseExtends();
		CreateThreadUseExtends myThread3=new CreateThreadUseExtends();
		mThread1.start();
		mThread2.start();
		myThread3.start();
	}
}
