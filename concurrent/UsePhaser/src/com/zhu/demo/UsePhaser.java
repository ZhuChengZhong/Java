package com.zhu.demo;

import java.util.concurrent.Phaser;

// ��Phaser���ж����Ϲ��ܣ��������ö��ͬ���� ��CyclicBarrier����
class MyRunnable implements Runnable{
	private Phaser phaser;
	public MyRunnable(Phaser phaser){
		this.phaser=phaser;
	}
	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName()+": task1 begin");
		phaser.arriveAndAwaitAdvance();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+": task1 end");
		System.out.println("������������"+phaser.getPhase());
		
		
		System.out.println(Thread.currentThread().getName()+": task2 begin");
		phaser.arriveAndAwaitAdvance();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+": task2 end");
		System.out.println("������������"+phaser.getPhase());
	}
	
}
public class UsePhaser {
    public static void main(String[] args) {
    	Phaser phaser=new Phaser(3);
		for(int i=0;i<3;i++){
			new Thread(new MyRunnable(phaser)).start();
		}
	}
}
