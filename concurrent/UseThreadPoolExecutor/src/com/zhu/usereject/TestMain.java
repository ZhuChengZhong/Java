package com.zhu.usereject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TestMain {
	public static void main(String[] args) {
		ExecutorService es=new ThreadPoolExecutor(2,2,1000,TimeUnit.SECONDS,new SynchronousQueue<Runnable>(),new MyRejectedExecutionHandler());
		for(int i=1;i<=4;i++){
			es.execute(new MyRunnable("ÈÎÎñ"+i));
		}
		es.shutdown();
	}
}
