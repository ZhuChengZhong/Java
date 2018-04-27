package com.zhu.myrpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RPCInvokeHandler {
	private int port;
	private Class c;
	private ExecutorService executeService=Executors.newCachedThreadPool();
	public RPCInvokeHandler(int port,Class c) {
		this.port=port;
		this.c=c;
	}
	
	private  class InvokeTask implements Runnable{
		private Socket socket;
		public InvokeTask(Socket socket) {
			this.socket=socket;
		}
		@Override
		public void run() {
			ObjectInputStream in=null;
			ObjectOutputStream out=null;
			try {
				in=new ObjectInputStream(socket.getInputStream());
				//读取要调用的方法名称
				String methodName=in.readUTF();
				//获取方法参数
				Object[] args=(Object[])in.readObject();
				//调用方法
				Object result=this.invoke(c, methodName, args);
				//返回结果
				out=new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(result);
			} catch (Exception e) {
				if(out!=null) {
					try {
						out.writeObject(e);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}finally {
				if(in!=null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(out!=null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(socket!=null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
		/**
		 * 调用指定方法
		 * @param c
		 * @param methodName
		 * @param args
		 * @return
		 * @throws Exception
		 */
		private Object invoke(Class c,String methodName,Object[] args) throws Exception {
			Object o=c.newInstance();
			Class[] classes=new Class[args.length];
			for(int i=0;i<args.length;i++) {
				classes[i]=args[i].getClass();
			}
			Method method=c.getDeclaredMethod(methodName,classes);
			return method.invoke(o, args);
		}
	}
	
	public void start() throws IOException {
		ServerSocket  serverSocket=new ServerSocket(port);
		while(true) {
			Socket socket=serverSocket.accept();
			executeService.execute(new InvokeTask(socket));
		}
	}
}
