package com.zhu.myrpc.test;

public class HelloServiceImpl implements HelloService{

	@Override
	public String hello(String one, String two) {
		return one + "say hello to "+two;
	}

}
