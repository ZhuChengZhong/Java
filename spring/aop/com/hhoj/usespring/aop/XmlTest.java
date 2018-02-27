package com.hhoj.usespring.aop;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlTest {
	public static void main(String[] args) {
		
		ApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");
		AObject target=(AObject)application.getBean("target");
		target.method1(1);
		target.method2();
	}
}
