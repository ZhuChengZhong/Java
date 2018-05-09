package com.zhu.demo1;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
		return "返回的数据";
	}
	
}
