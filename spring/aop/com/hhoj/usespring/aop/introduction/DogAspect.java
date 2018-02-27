package com.hhoj.usespring.aop.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DogAspect {
	
	@DeclareParents(value="com.hhoj.usespring.aop.introduction.Dog",defaultImpl=ICounter.class)
	public Counter counter;
}
