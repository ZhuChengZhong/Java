package com.zhu.testblock;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
//测试ExChange的exchange方法是否阻塞
// exchange 有两个方法  一个会一直阻塞，另一个可以设置阻塞时间
class ThreadA extends Thread{
	private Exchanger<String> ex;
	public ThreadA(Exchanger<String> ex){
		this.ex=ex;
	}
	@Override
	public void run() {
		System.out.println("进入线程："+Thread.currentThread().getName());
		try {
			/*System.out.println(ex.exchange("获取的值"));*/
			
			try {
				System.out.println(ex.exchange("值",2,TimeUnit.SECONDS));
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("线程退出");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class ThreadB extends Thread{
	private Exchanger<String> ex;
	public ThreadB(Exchanger<String> ex){
		this.ex=ex;
	}
	@Override
	public void run() {
		System.out.println("进入线程："+Thread.currentThread().getName());
			try {
				try {
					System.out.println(ex.exchange("值",2,TimeUnit.SECONDS));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (TimeoutException e) {
				System.out.println("获取超时");
				e.printStackTrace();
			}
			System.out.println("线程退出");
		}
}

public class TestExChangeBlock {
     public static void main(String[] args) {
		Exchanger<String> ex=new Exchanger<String>();
		ThreadA threadA=new ThreadA(ex);
		threadA.start();
		System.out.println("main end");
	}
}
