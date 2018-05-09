package com.zhu.demo;

import java.util.concurrent.Phaser;
//arriveAndDeregister可以减少parties的值  而CyclicBarrier不可以
class MyThread extends Thread{
	private Phaser phaser;
	public MyThread(Phaser phaser){
		this.phaser=phaser;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+": task1 begin");
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName()+": task1 end");
	}
}
public class TestDeRegister {
	public static void main(String[] args) throws Exception{
		Phaser phaser=new Phaser(2);
		MyThread t=new MyThread(phaser);
		t.start();
		Thread.sleep(3000);
		phaser.arriveAndDeregister();
	}
}
