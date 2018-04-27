package com.zhu.myrpc.test;

import com.zhu.myrpc.RPCProxyFactory;

public class Client {
	public static void main(String[] args) {
		HelloService helloService=RPCProxyFactory.create(8888, "localhost", HelloService.class);
		for(int i=0;i<20;i++) {
			String result=helloService.hello("zhu"+i, "li"+i);
			System.out.println(result);
		}
	}
}
