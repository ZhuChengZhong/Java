package com.zhu.custompoll;

public class Task implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("执行任务");
			Thread.sleep(3000);
			System.out.println("任务完成");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
