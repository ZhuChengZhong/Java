package com.zhu.awaittermination;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class MyRunnable implements Runnable{
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println("任务执行完毕");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
public class UseAwaitTermination {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es=new ThreadPoolExecutor(2, 333, 1000, TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(10));
		MyRunnable task=new MyRunnable();
		es.execute(task);
		es.execute(task);
		es.execute(task);
		es.shutdown();
		System.out.println(es.awaitTermination(3000, TimeUnit.SECONDS));
		System.out.println("main end");
	}
}
