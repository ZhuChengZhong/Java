package com.hhoj.usespring.aop.introduction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component("dog")
public class Dog {
	public void talk(){
		System.out.println("wang wang wang  !!");
	}
}
