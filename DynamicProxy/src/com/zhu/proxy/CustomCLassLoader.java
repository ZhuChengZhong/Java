package com.zhu.proxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 自定义类加载器
 * @author zhu
 *
 */
public class CustomCLassLoader extends ClassLoader{
	private String dir;
	private String proxyPackage;
	
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getProxyPackage() {
		return proxyPackage;
	}
	public void setProxyPackage(String proxyPackage) {
		this.proxyPackage = proxyPackage;
	}
	public CustomCLassLoader(String dir,String proxyPackage) {
		this.dir=dir;
		this.proxyPackage=proxyPackage;
	}
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			ByteBuffer buffer=ByteBuffer.allocate(1024);
			RandomAccessFile file=new RandomAccessFile(dir+name+".class", "r");
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			FileChannel channel=file.getChannel();
			int len;
			while((len=channel.read(buffer))>0) {
				out.write(buffer.array(),0,len);
				buffer.clear();
			}
			return this.defineClass(proxyPackage+"."+name,out.toByteArray(), 0, out.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}
	 
}
