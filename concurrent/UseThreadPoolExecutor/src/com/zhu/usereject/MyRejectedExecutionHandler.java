package com.zhu.usereject;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedExecutionHandler implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		MyRunnable runnable=(MyRunnable)r;
		System.out.println("����:"+runnable.getTaskName()+",���ܾ�ִ��");
		
	}
}
