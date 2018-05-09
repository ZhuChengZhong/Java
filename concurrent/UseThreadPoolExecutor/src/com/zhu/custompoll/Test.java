package com.zhu.custompoll;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		/*MyThreadPool pool=new MyThreadPool(3, 5, 5000, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>(),new MyThreadFactory());*/
		MyThreadPool pool=new MyThreadPool(3, 5, 1000, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1),new MyThreadFactory());
		
		for(int i=0;i<5;i++){
			pool.execute(new Task());
		}
		System.out.println("getActiveCount:"+pool.getActiveCount());
		System.out.println("getPoolSize:"+pool.getPoolSize());
		System.out.println("getQueue().size():"+pool.getQueue().size());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("getActiveCount:"+pool.getActiveCount());
		System.out.println("getPoolSize:"+pool.getPoolSize());
		System.out.println("getQueue().size():"+pool.getQueue().size());
		pool.shutdown();
	}
}
