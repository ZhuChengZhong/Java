package com.zhu.shutdown;

public class MyRunnable implements Runnable{
	@Override
	public void run() {
		//如果当前线程被中断则退出
		while(!Thread.currentThread().isInterrupted()){
			System.out.println("我还在运行");
			try {
				Thread.sleep(3000);
			 //抛出异常后中断状态会被清除，所以要重新设置中断状态
			} catch (InterruptedException e) {
				System.out.println("被中断了");
				Thread.currentThread().interrupt();
				//只返回当前的中断状态
				System.out.println(Thread.currentThread().isInterrupted());
				//interrupted()返回当前中断状态　　并清除中断状态　
				/*System.out.println(Thread.currentThread().interrupted());*/
				e.printStackTrace();
			}
		}
		System.out.println("退出循环");
	}
}
