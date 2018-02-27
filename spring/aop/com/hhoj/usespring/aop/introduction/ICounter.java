package com.hhoj.usespring.aop.introduction;

public class ICounter implements Counter{
	private Integer count;
	public ICounter(){
		count=0;
	}
	public int getCount() {
		return ++count;
	}
}
