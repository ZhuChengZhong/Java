package com.hhoj.usespring;

import java.util.regex.Pattern;

import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.spring.entity.StudentService;

public class Demo3 {
	public static void main(String[] args) {
		ApplicationContext application=new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentService studentService=(StudentService)application.getBean("studentService");
		studentService.insert(3);
	}
}
