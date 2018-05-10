package com.zhu.proxy;

import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler{
	private Service service;
	public MyInvocationHandler(Service service) {
		this.service=service;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before method");
		Object result=method.invoke(service, args);
		System.out.println("after method");
		return result;
	}

}
