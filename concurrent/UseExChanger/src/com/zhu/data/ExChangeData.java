package com.zhu.data;

import java.util.concurrent.Exchanger;
class ThreadA extends Thread{
	private Exchanger<String> ex;
	public ThreadA(Exchanger<String> ex){
		this.ex=ex;
	}
	@Override
	public void run() {
		
		try {
			String data=ex.exchange("A线程的值");
			System.out.println("线程A 获取的值为："+data);
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
		
		try {
			String data=ex.exchange("B线程的值");
			System.out.println("线程B 获取的值为："+data);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
public class ExChangeData {
	
	
	public static void main(String[] args) {
		Exchanger<String> ex=new Exchanger<String>();
		ThreadA tA=new ThreadA(ex);
		ThreadB tB=new ThreadB(ex);
		tA.start();
		tB.start();
	}
}
