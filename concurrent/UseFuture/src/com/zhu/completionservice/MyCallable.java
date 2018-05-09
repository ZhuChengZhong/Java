package com.zhu.completionservice;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String>{
	private String userName;
	private int waitTime;
	public MyCallable(String userName,int waitTime) {
		this.userName=userName;
		this.waitTime=waitTime;
	}
	@Override
	public String call() throws Exception {
		Thread.sleep(waitTime);
		return userName+"  ·µ»ØÊý¾Ý";
	}

}
