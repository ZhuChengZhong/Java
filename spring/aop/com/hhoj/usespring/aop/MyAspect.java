package com.hhoj.usespring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	@Pointcut(value="execution(public void method*(int ))")
	public void pointcut1(){ }
	
	@Around("pointcut1()")
	public void around(ProceedingJoinPoint joinPoint){
		System.out.println("before"+joinPoint.getTarget().getClass());
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("after"+joinPoint.getTarget().getClass());
	}
}
