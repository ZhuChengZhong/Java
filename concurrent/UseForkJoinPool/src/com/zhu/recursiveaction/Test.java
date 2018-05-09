package com.zhu.recursiveaction;

import java.util.concurrent.ForkJoinPool;
//使用RecursiveAction演示任务的分解
public class Test {
	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool=new ForkJoinPool();
		pool.execute(new MyRecursiveAction(1,5));
		/*pool.submit(new MyRecursiveAction());*/
		Thread.sleep(4000);
	}
}
