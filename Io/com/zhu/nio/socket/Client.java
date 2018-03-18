package com.zhu.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class Client {
	private Selector selector;
	private SocketChannel socketChannel;
	public void init() throws IOException {
		Selector selector=Selector.open();
		socketChannel=SocketChannel.open(new InetSocketAddress("localhost", 9999));
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
	}
	public void send(String message) throws IOException {
		ByteBuffer byteBuffer=ByteBuffer.wrap(message.getBytes());
		socketChannel.write(byteBuffer);
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		Client client=new Client();
		client.init();
		while(true) {
			client.send("zhuchengzhong");
			TimeUnit.SECONDS.sleep(10);
			
		}
	}
}
