package com.zhu.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class HttpHandler implements Runnable{

	private SelectionKey key;
	
	public HttpHandler(SelectionKey key) {
		this.key=key;
	}
	//处理连接事件
	public void handlerAccept(SelectionKey key)throws IOException{
		ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
		SocketChannel sc = ssc.accept();
		if (sc!=null) {
			sc.configureBlocking(false);
			sc.register(key.selector(),SelectionKey.OP_READ,ByteBuffer.allocate(1024));
		}
	}
	//处理读取事件
	public void handlerRead(SelectionKey key)throws IOException{
		SocketChannel sc=(SocketChannel)key.channel();
		ByteBuffer bb=(ByteBuffer)key.attachment();
		bb.clear();
		int len;
		byte[] bytes=new byte[1024];
		StringBuilder sb=new StringBuilder();
		while((len=sc.read(bb))>0) {
			//切换成读模式
			bb.flip();
			bb.get(bytes,0,len);
			sb.append(new String(bytes,0,len));
			//使用过后需要清空buffer以备下次写
			bb.clear();
		}
		String receive=sb.toString();
		System.out.println(receive);
		
		//拼接响应消息
		StringBuilder response=new StringBuilder();
		response.append("HTTP/1.1 200 OK\r\n");
		response.append("Content-Type:text/html;charset=utf-8\r\n");
		response.append("\r\n");
		response.append("<html><head><title>自己實現Http服務器</title></head><body>");
		response.append("<h1>歡迎光臨</h1>");
		response.append("</body></html>");
		ByteBuffer byteBuffer=ByteBuffer.wrap(response.toString().getBytes());
		while(byteBuffer.hasRemaining()) {
			sc.write(byteBuffer);
		}
		sc.close();
	}


	@Override
	public void run() {
		// 连接事件
		if (key.isAcceptable()) {
			try {
				handlerAccept(key);
			} catch (IOException e) {
				e.printStackTrace();
			}
		// 数据可读事件
		} else if (key.isReadable()) {
			try {
				handlerRead(key);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
