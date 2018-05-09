package com.zhu.getandjoin;

import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<String>{

	@Override
	protected String compute() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s=null;
		s.toString();
		return "返回的数据";
	}

}
