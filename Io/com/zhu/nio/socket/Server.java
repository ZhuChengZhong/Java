package com.zhu.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
	public void selector() throws IOException {
		ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
		Selector selector=Selector.open();
		ServerSocketChannel socketChannel=ServerSocketChannel.open(); 
		//����Ϊ��������ʽIO
				socketChannel.configureBlocking(false);
		socketChannel.socket().bind(new InetSocketAddress(9999));
		
		//ע��������¼�
		socketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while(true) {
			if(selector.select()==0) {
				continue ;
			}
			System.out.println("--------------------");
			Set<SelectionKey> set=selector.selectedKeys();
			Iterator<SelectionKey>it= set.iterator();
			while (it.hasNext()) {
				SelectionKey key= it.next();
				if((key.readyOps()& SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT) {
					System.out.println("----------------���µ���������------------------------");
					ServerSocketChannel serverSocketChannel=(ServerSocketChannel)key.channel();
					SocketChannel clientChannel=serverSocketChannel.accept();
					clientChannel.configureBlocking(false);
					clientChannel.register(selector, SelectionKey.OP_READ);
				}else if((key.readyOps()& SelectionKey.OP_READ)==SelectionKey.OP_READ) {
					System.out.println("----------------���µ����ݿɶ�------------------------");
					SocketChannel clientChannel=(SocketChannel)key.channel();
					clientChannel.read(byteBuffer);
					byteBuffer.flip();
					byteBuffer.reset();
					byte[] bytes=new byte[1024];
					int len;
					if((len=byteBuffer.remaining())>0) {
						byteBuffer.get(bytes,0,len);
						System.out.print(new String(bytes,0,len));
					}
					byteBuffer.clear();
				}
				it.remove();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server().selector();
	}
}
