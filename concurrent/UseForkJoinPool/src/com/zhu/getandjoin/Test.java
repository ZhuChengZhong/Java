package com.zhu.getandjoin;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
//get join 都可以获取结果
//get方法遇到异常的时候可以捕获，而join方法遇到异常则直接抛出
public class Test {
	public static void main(String[] args) {
		ForkJoinPool pool=new ForkJoinPool();
		ForkJoinTask<String> forkJoinTask= pool.submit(new MyRecursiveTask());
		try {
			String result=forkJoinTask.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException 异常");
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("ExecutionException 异常");
			e.printStackTrace();
		}
		
		/*String result=forkJoinTask.join();
		System.out.println(result);*/
	}
}
