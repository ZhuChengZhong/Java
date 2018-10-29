package com.zhu.file.server.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	public static void main(String[] args) {
		int port=12345;
		try {
			System.out.println("server start..");
			bind(port);
			System.out.println("server end..");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static class ServerChannelHandler extends ChannelHandlerAdapter{

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			ByteBuf bb=(ByteBuf)msg;
			int size=bb.readableBytes();
			byte[] data=new byte[size];
			bb.readBytes(data, 0, size);
			System.out.println(new String(data));
		}
		
	}
	public static void bind(int port) throws InterruptedException {
		EventLoopGroup accepter=new NioEventLoopGroup();
		EventLoopGroup worker=new NioEventLoopGroup();
		try{
			ServerBootstrap bootstrap=new ServerBootstrap();
			bootstrap.group(accepter, worker)
			.option(ChannelOption.SO_BACKLOG,100)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>(){
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ServerChannelHandler());
				}
			});
			ChannelFuture future=bootstrap.bind(port).sync();
			
			future.channel().closeFuture().sync();
		}finally {
			accepter.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
}
