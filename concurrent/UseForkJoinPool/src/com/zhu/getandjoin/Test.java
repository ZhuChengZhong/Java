package com.zhu.getandjoin;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
//get join �����Ի�ȡ���
//get���������쳣��ʱ����Բ��񣬶�join���������쳣��ֱ���׳�
public class Test {
	public static void main(String[] args) {
		ForkJoinPool pool=new ForkJoinPool();
		ForkJoinTask<String> forkJoinTask= pool.submit(new MyRecursiveTask());
		try {
			String result=forkJoinTask.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException �쳣");
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("ExecutionException �쳣");
			e.printStackTrace();
		}
		
		/*String result=forkJoinTask.join();
		System.out.println(result);*/
	}
}
