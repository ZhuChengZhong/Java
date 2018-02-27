package com.hhoj.usespring.aop.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		Counter dog1=(Counter)application.getBean("dog");
		Counter dog2=(Counter)application.getBean("dog");
		System.out.println(dog1.getCount());
		System.out.println(dog2.getCount());
	}
}
