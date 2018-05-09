package com.zhu.demo;

import java.util.concurrent.Phaser;

// 类Phaser具有多屏障功能，可以设置多个同步点 与CyclicBarrier类似
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
		System.out.println("到达屏障数："+phaser.getPhase());
		
		
		System.out.println(Thread.currentThread().getName()+": task2 begin");
		phaser.arriveAndAwaitAdvance();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+": task2 end");
		System.out.println("到达屏障数："+phaser.getPhase());
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
