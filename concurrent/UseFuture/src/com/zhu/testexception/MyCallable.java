package com.zhu.testexception;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String>{
	
	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		if(1==1){
			throw new NullPointerException();
		}
		return "·µ»ØÊý¾Ý";
	}

}
