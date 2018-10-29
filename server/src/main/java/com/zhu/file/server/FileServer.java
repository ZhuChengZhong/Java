package com.zhu.file.server;

import java.io.File;
import java.io.RandomAccessFile;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

public class FileServer {
	
	private static final int FILE_SERVER_DEFAULT_PORT=8888;
	
	private static final String DEFAULT_ROOT_PATH="F:\\rootpath";
	
	private static class FileServerChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

		@Override
		protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
			if(!msg.decoderResult().isSuccess()) {
				sendErrorMsg(ctx,HttpResponseStatus.BAD_REQUEST);
				return ;
			}
			if (msg.method().compareTo(HttpMethod.GET)!=0) {
				sendErrorMsg(ctx,HttpResponseStatus.METHOD_NOT_ALLOWED);
				return ;
			}
			String url=msg.uri();
			url=url.replace("/",File.separator);
			String filePath=DEFAULT_ROOT_PATH+url;
			File file=new File(filePath);
			if(!file.exists()) {
				sendErrorMsg(ctx,HttpResponseStatus.NOT_FOUND);
				return ;
			}
			if(file.isFile()) {
				DefaultHttpResponse response=new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
				RandomAccessFile raf=new RandomAccessFile(file, "r");
				setHttpContentType(file.getName(),response);
				response.headers().set("Content-Length", raf.length()+"");
				ctx.write(response);
				ChunkedFile chunkedFile=new ChunkedFile(raf, 0, raf.length(), 1024);
				ctx.writeAndFlush(chunkedFile);
			} else {
				FullHttpResponse fhResponse=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK);
				fhResponse.headers().set("Content-Type", "text/html;charset=utf-8");
				StringBuilder sb=new StringBuilder();
				sb.append("<html>\n").append("<body>\n").append("<ul>\n");
				for(File f:file.listFiles()) {
					sb.append("<li>");
					String path=getUrlFormPath(f.getPath());
					sb.append("<a href='"+path+"'>"+f.getName()+"</a>");
					sb.append("</li>\n");
				}
				sb.append("</ul>\n").append("</body>\n").append("</html>\n");
				ByteBuf bb=Unpooled.copiedBuffer(sb,CharsetUtil.UTF_8);
				fhResponse.headers().set("Content-Length", bb.readableBytes()+"");
				fhResponse.content().writeBytes(bb);
				System.out.println(bb.readableBytes());
				ctx.writeAndFlush(fhResponse);
			}
			
		}
		
		private void setHttpContentType(String filePath,DefaultHttpResponse response) {
			if(filePath==null) {
				System.out.println("filePath cant be null");
				return ;
			}
			filePath=filePath.toLowerCase();
			if(filePath.endsWith(".txt")) {
				response.headers().set("Content-Type", "text/plain");
			}else if(filePath.endsWith(".gif")) {
				response.headers().set("Content-Type", "image/gif");
			}else if(filePath.endsWith(".jpg")) {
				response.headers().set("Content-Type", " image/jpeg");
			}else if(filePath.endsWith(".png")) {
				response.headers().set("Content-Type", " image/png");
			}else if(filePath.endsWith(".xml")) {
				response.headers().set("Content-Type", "application/xml");
			}else if(filePath.endsWith(".pdf")) {
				response.headers().set("Content-Type", "application/pdf");
			}else if(filePath.endsWith(".html")) {
				response.headers().set("Content-Type", "text/html");
			}else if(filePath.endsWith(".docx")){
				response.headers().set("Content-Type", "applicationo/msword");
			}else {
				response.headers().set("Content-Type", "applicationo/ctet-stream");
			}
		}
		
		private void sendErrorMsg(ChannelHandlerContext ctx,HttpResponseStatus status) {
			DefaultHttpResponse httpResponse=new DefaultHttpResponse(HttpVersion.HTTP_1_1,status);
			ctx.writeAndFlush(httpResponse);
		}
		
		private String getUrlFormPath(String filePath) {
			if(filePath==null || !filePath.startsWith(DEFAULT_ROOT_PATH)) {
				System.out.println("error path :"+filePath);
				return null;
			}
			return filePath.substring(DEFAULT_ROOT_PATH.length());
		}
	}
	
	public static void bind(int port) throws Exception {
		ServerBootstrap bootstrap=new ServerBootstrap();
		EventLoopGroup acceptor=new NioEventLoopGroup();
		EventLoopGroup worker=new NioEventLoopGroup();
		try {
			bootstrap.group(acceptor, worker)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG,100)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new HttpRequestDecoder());
					ch.pipeline().addLast(new HttpObjectAggregator(65535));
					ch.pipeline().addLast(new HttpResponseEncoder());
					ch.pipeline().addLast(new ChunkedWriteHandler());
					ch.pipeline().addLast(new FileServerChannelHandler());
				}
			});
			ChannelFuture future=bootstrap.bind(port).sync();
			System.out.println("file server start success");
			future.channel().closeFuture().sync();
		}finally {
			acceptor.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("file server start..");
			bind(FILE_SERVER_DEFAULT_PORT);
		} catch (Exception e) {
			System.out.println("file server error "+e.getMessage());
		}
	}
	
}
