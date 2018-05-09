package com.zhu.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseCompletionService {
	public static void main(String[] args) {
		ExecutorService es=new ThreadPoolExecutor(3,5,1000,TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());
		CompletionService<String> cs=new ExecutorCompletionService<String>(es);
		for(int i=1;i<=5;i++){
			MyCallable callable=new  MyCallable("task"+i,(6-i)*2000);
			cs.submit(callable);
		}
		for(int i=1;i<=5;i++){
			try {
				String result=cs.take().get();
				System.out.println(result);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		es.shutdown();
	}
}
