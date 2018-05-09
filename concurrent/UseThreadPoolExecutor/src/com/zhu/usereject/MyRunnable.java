package com.zhu.usereject;

public class MyRunnable implements Runnable{
	private String taskName;
	public String getTaskName(){
		return taskName;
	}
	public MyRunnable(String taskName){
		this.taskName=taskName;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			System.out.println("»ŒŒÒ÷¥––");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
