package com.zhu.file.server.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	public static void main(String[] args) {
		
	}
	
	
	private static class ClientHandler extends ChannelOutboundHandlerAdapter {
		
	}
	
	public static void connect(String host,int port) {
		Bootstrap bootstrap=new Bootstrap();
		EventLoopGroup worker=new NioEventLoopGroup();
		bootstrap.group(worker)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ClientHandler());
			}
		});
	}
}
