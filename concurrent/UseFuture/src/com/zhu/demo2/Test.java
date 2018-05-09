package com.zhu.demo2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es=new ThreadPoolExecutor(2,3,5,TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());
		/*Future<String>f=es.submit(new MyCallable());*/
		//测试invokeAll有没有阻塞效果
		//结果：invokeAll具有阻塞效果
		List<Future<String>>list=es.invokeAll(Arrays.asList(new MyCallable()));
		System.out.println("------");
		System.out.println(list.get(0).get());
		es.shutdown();
	}
}
