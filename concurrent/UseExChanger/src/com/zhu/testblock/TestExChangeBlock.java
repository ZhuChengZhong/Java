package com.zhu.testblock;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
//����ExChange��exchange�����Ƿ�����
// exchange ����������  һ����һֱ��������һ��������������ʱ��
class ThreadA extends Thread{
	private Exchanger<String> ex;
	public ThreadA(Exchanger<String> ex){
		this.ex=ex;
	}
	@Override
	public void run() {
		System.out.println("�����̣߳�"+Thread.currentThread().getName());
		try {
			/*System.out.println(ex.exchange("��ȡ��ֵ"));*/
			
			try {
				System.out.println(ex.exchange("ֵ",2,TimeUnit.SECONDS));
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("�߳��˳�");
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
		System.out.println("�����̣߳�"+Thread.currentThread().getName());
			try {
				try {
					System.out.println(ex.exchange("ֵ",2,TimeUnit.SECONDS));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (TimeoutException e) {
				System.out.println("��ȡ��ʱ");
				e.printStackTrace();
			}
			System.out.println("�߳��˳�");
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
