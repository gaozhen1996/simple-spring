package com.gz.javastudy.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
* <p>
* Description:
* 用实现Callable的方式，来实现多线程
* <p>
* @author gaozhen
* @date 2021年3月13日
* @Version 1.1
 */
public class CreateThreadUseImplCall implements Callable<String>{

	@Override
	public String call() throws Exception {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
		return "执行完成";
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//Callable的核心方法为call()，有返回值
		Callable<String> callableExe = new CreateThreadUseImplCall();
		//FutureTask表示异步任务，是还没有完成的任务给出的未来结果
		FutureTask[] futureTasks = new FutureTask[3];
		for(int i = 0;i<3;i++) {
			futureTasks[i]=new FutureTask<>(callableExe);
			new Thread(futureTasks[i]).start();
		}
		for (FutureTask futureTask : futureTasks) {
			System.out.println(futureTask.get());
		}
	}

}
