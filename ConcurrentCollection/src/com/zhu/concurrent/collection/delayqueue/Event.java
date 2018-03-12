package com.zhu.concurrent.collection.delayqueue;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Event implements Delayed{
	private String eventName;
	private Date happenDate;
	public Event(Date happenDate,String eventName){
		this.happenDate=happenDate;
		this.eventName=eventName;
	}
	@Override
	public int compareTo(Delayed o) {
		long diff=this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS);
		return diff<0?-1:(diff==0)?0:1;
	}
	
	
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@Override
	public long getDelay(TimeUnit unit) {
		return happenDate.getTime()-System.currentTimeMillis();
	}

}
