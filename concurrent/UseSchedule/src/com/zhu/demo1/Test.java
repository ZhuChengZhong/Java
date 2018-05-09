package com.zhu.demo1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//测试延时运行任务
		ScheduledExecutorService ses=Executors.newScheduledThreadPool(2);
		ScheduledFuture<String>future1=ses.schedule(new MyCallable(),3,TimeUnit.SECONDS);
		ScheduledFuture<?>future2=ses.schedule(new MyRunnable(), 2,TimeUnit.SECONDS);
		//getDelay返回对应任务剩余的延迟时间
		System.out.println(future1.getDelay(TimeUnit.SECONDS));
		System.out.println(future2.getDelay(TimeUnit.SECONDS));
		System.out.println(future1.get());
		System.out.println("---------");
		System.out.println(future1.getDelay(TimeUnit.SECONDS));
		System.out.println(future2.getDelay(TimeUnit.SECONDS));
		System.out.println(future2.get());
		
		//测试循环运行任务
		//循环执行任务不能有返回值 ，也就是只能使用Runnable 不能使用Callable 
		/*ScheduledExecutorService ses=new ScheduledThreadPoolExecutor(2);
		ScheduledFuture<?> future=ses.scheduleAtFixedRate(new MyRunnable(),1, 1,TimeUnit.SECONDS);
		System.out.println(future.getDelay(TimeUnit.SECONDS));*/
		//ses.shutdown();
	}
}
