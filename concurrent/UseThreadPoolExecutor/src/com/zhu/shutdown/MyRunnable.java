package com.zhu.shutdown;

public class MyRunnable implements Runnable{
	@Override
	public void run() {
		//�����ǰ�̱߳��ж����˳�
		while(!Thread.currentThread().isInterrupted()){
			System.out.println("�һ�������");
			try {
				Thread.sleep(3000);
			 //�׳��쳣���ж�״̬�ᱻ���������Ҫ���������ж�״̬
			} catch (InterruptedException e) {
				System.out.println("���ж���");
				Thread.currentThread().interrupt();
				//ֻ���ص�ǰ���ж�״̬
				System.out.println(Thread.currentThread().isInterrupted());
				//interrupted()���ص�ǰ�ж�״̬����������ж�״̬��
				/*System.out.println(Thread.currentThread().interrupted());*/
				e.printStackTrace();
			}
		}
		System.out.println("�˳�ѭ��");
	}
}
