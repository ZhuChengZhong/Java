package com.zhu.custompoll;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory{
		private static int count;
	@Override
	public Thread newThread(Runnable r) {
		Thread th=new Thread(r);
		System.out.println(++count);
		th.setName("我的线程");
		return th;
	}
    
}
