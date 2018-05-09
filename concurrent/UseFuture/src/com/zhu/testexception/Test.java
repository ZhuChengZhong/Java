package com.zhu.testexception;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//当使用invokeAny时  只返回一个最快执行完的任务的结果，其他任务抛出异常不会传递到主线程，需要在抛出异常的线程中自己处理
//如果抛出异常的任务先执行完，不会被返回值，直到有一个正常执行的任务然后返回，如果一直到最后都没有返回值则抛出异常，抛出的异常是最后出现的异常
public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es=new ThreadPoolExecutor(2,3,5,TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());
		/*List<Future<String>>list=es.invokeAll(Arrays.asList(new MyCallable()));*/
		String result=es.invokeAny(Arrays.asList(new MyCallableA(),new MyCallable()));
		System.out.println(result);
		es.shutdown();
	}
}
