package com.hhoj.usespring.aop;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		
		AspectJProxyFactory factory=new AspectJProxyFactory();
		factory.setProxyTargetClass(true);
		factory.setTarget(new AObject());
		factory.addAspect(MyAspect.class);
		
		AObject proxy=(AObject)factory.getProxy();
		System.out.println(proxy.getClass().getName());
		proxy.method1(1);
		proxy.method2();
	}
}
