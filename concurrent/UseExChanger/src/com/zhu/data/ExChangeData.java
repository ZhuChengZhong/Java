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
			String data=ex.exchange("A�̵߳�ֵ");
			System.out.println("�߳�A ��ȡ��ֵΪ��"+data);
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
			String data=ex.exchange("B�̵߳�ֵ");
			System.out.println("�߳�B ��ȡ��ֵΪ��"+data);
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
