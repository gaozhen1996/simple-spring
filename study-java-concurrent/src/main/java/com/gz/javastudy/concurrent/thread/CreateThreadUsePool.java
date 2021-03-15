package com.gz.javastudy.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 
* <p>
* Description:
* 使用线程池的方式来创建线程
* <p>
* @author gaozhen
* @date 2021年3月13日
* @Version 1.1
 */
public class CreateThreadUsePool implements Callable<String>{
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/**
		 * 创建线程池
		 */
		ExecutorService executorService = Executors.newCachedThreadPool();
		/**
		 * 往线程池中添加任务
		 */
		Callable<String> callableExe = new CreateThreadUsePool();
		FutureTask[] futureTasks = new FutureTask[3];
		for(int i = 0;i<3;i++) {
			futureTasks[i]=(FutureTask) executorService.submit(callableExe);
		}
		/**
		 * 打印返回结果
		 */
		for (FutureTask futureTask : futureTasks) {
			System.out.println(futureTask.get());
		}
		/**
		 * 关闭线程池
		 */
		executorService.shutdown();
	}

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName());
		return "执行完成";
	}
}
