package com.zhu.custompoll;

public class Task implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("ִ������");
			Thread.sleep(3000);
			System.out.println("�������");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
