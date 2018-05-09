package com.zhu.testexception;

import java.util.concurrent.Callable;

public class MyCallableA implements Callable<String>{
	
	@Override
	public String call() throws Exception {
		Thread.sleep(2000);
		return "·µ»ØÊý¾Ý";
	}

}
