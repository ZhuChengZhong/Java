package com.hhoj.usespring;

import java.util.Locale;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo2 {
	public static void main(String[] args) {
		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		String name=application.getMessage("menu.name",null,Locale.US);
		String name2=application.getMessage("menu.name",null,Locale.CHINA);
		System.out.println(name+","+name2);
		
	}
}
