package com.zhu.myrpc.test;

import java.io.IOException;

import com.zhu.myrpc.RPCInvokeHandler;

public class Server {
	public static void main(String[] args) throws IOException {
		RPCInvokeHandler handler=new RPCInvokeHandler(8888, HelloServiceImpl.class);
		handler.start();
	}
}
