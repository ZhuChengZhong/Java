package com.zhu.demo1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> futureTask=new FutureTask<String>(new MyCallable());
		futureTask.run();
		System.out.println(futureTask.get());
	}
}
