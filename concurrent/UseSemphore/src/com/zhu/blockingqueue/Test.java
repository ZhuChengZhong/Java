package com.zhu.blockingqueue;

public class Test {
  public static void main(String[] args) {
	MyBlockingQueue<String>queue=new MyBlockingQueue<String>();
	System.out.println("============");
	queue.put("aaa");
	queue.put("bbb");
	queue.put("ccc");
	queue.put("ddd");
	String s=queue.take();
	System.out.println(s);
  }
}
