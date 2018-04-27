package com.zhu.myrpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class RPCProxyFactory{
	@SuppressWarnings("unchecked")
	public static <T> T create(int port,String host,Class c){
		return (T)Proxy.newProxyInstance(c.getClassLoader(),new Class[]{c},new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Socket socket=null;
				ObjectOutputStream out=null;
				ObjectInputStream in=null;
				try {
					socket=new Socket(host, port);
					out=new ObjectOutputStream(socket.getOutputStream());
					//传递要调用的方法名
					out.writeUTF(method.getName());
					//发送参数
					out.writeObject(args);
					//接收远程调用返回结果
					in=new ObjectInputStream(socket.getInputStream());
					Object result=in.readObject();
					if(result instanceof Throwable) {
						throw (Throwable)result;
					}
					return result;
				}finally {
					if(in!=null) {
						in.close();
					}
					if(out!=null) {
						out.close();
					}
					if(socket!=null) {
						socket.close();
					}
				}
			}
		});
	}
}
