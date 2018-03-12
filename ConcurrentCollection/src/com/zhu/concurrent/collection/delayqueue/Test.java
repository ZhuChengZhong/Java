package com.zhu.concurrent.collection.delayqueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class Test {
	public static void main(String[] args) {
		DelayQueue<Event> queue=new DelayQueue<Event>();
		queue.add(new Event(new Date(System.currentTimeMillis()+10000),"late"));
		queue.add(new Event(new Date(System.currentTimeMillis()+5000),"early"));
		while(queue.size()>0) {
			try {
				Event event=queue.take();
				System.out.println(event.getEventName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
