package com.zhu.test;
class BigClass{
	int a;
	int b;
	public BigClass(){
		a=1;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b=2;
	}
}
public class Test {
	private static BigClass big;
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				big=new BigClass();
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			System.out.println(big.a);
			System.out.println(big.b);
			}
		}).start();
	}
}
