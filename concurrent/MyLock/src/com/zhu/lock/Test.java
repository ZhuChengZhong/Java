package com.zhu.lock;

import java.util.concurrent.locks.ReentrantLock;
/**
 * ≤‚ ‘MyExclusiveLock
 * @author Zhu
 *
 */
public class Test {
	private static int count;
	private static Object lock=new Object();
	private static MyExclusiveLock myLock=new MyExclusiveLock();
/*	static class AddCountTask implements Runnable{
		
		@Override
		public void run() {
			for(int i=0;i<1000;i++){
				synchronized(lock){
					count++;
				}
			}
		}
	}*/
	static class AddCountTask implements Runnable{
		
		@Override
		public void run() {
			for(int i=0;i<1000;i++){
					myLock.lock();
					count++;
					myLock.unlock();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<100;i++){
			Thread thread=new Thread(new AddCountTask());
			thread.start();
		}
		Thread.sleep(10000);
		System.out.println(count);
	}
}
