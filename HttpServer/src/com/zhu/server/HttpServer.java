package com.zhu.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.zhu.handler.HttpHandler;
/**
 * Http服务器
 * @author zhu
 *
 */
public class HttpServer {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel ssc=ServerSocketChannel.open();
		//绑定端口
		ssc.socket().bind(new InetSocketAddress(33333));
		//设置未非阻塞模式
		ssc.configureBlocking(false);
		Selector selector=Selector.open();
		//注册连接监听
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		while(true) {
			int select=selector.select();
			if(select>0) {
				Set<SelectionKey>keys=selector.selectedKeys();
				Iterator<SelectionKey>it=keys.iterator();
				while(it.hasNext()) {
					SelectionKey key=it.next();
					new Thread(new HttpHandler(key)).start();
					it.remove();
				}
			}
		}
	}
}
