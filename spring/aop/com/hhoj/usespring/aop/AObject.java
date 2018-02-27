package com.hhoj.usespring.aop;

import org.springframework.stereotype.Component;

@Component("target")
public class AObject {
	public void method1(int a){
		System.out.println("method1");
	}
	public void method2(){
		System.out.println("method2");
	}
}
