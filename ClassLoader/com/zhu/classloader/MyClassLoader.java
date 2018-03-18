package com.zhu.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyClassLoader extends ClassLoader{
	private String classpath;
	public MyClassLoader(String classpath) {
		this.classpath=classpath;
	}

	
/*	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		String fileName=classpath+"/"+name.replaceAll("\\.", "/")+".class";
		File file=new File(fileName);
		ByteArrayOutputStream out=null;
		try {
			FileInputStream inputStream=new FileInputStream(file);
			out=new ByteArrayOutputStream();
			byte []b =new byte[1024];
			int len;
			while((len=inputStream.read(b))>0){
				out.write(b, 0, len);
			}
		} catch (Exception e) {
			throw new ClassNotFoundException();
		}
		
		return defineClass(out.toByteArray(),0, out.size());
	}*/

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		name=name.substring(1);
		String fileName=classpath+"/"+name.replaceAll("\\.", "/")+".class";
		File file=new File(fileName);
		ByteArrayOutputStream out=null;
		try {
			FileInputStream inputStream=new FileInputStream(file);
			out=new ByteArrayOutputStream();
			byte []b =new byte[1024];
			int len;
			while((len=inputStream.read(b))>0){
				out.write(b, 0, len);
			}
		} catch (Exception e) {
			throw new ClassNotFoundException();
		}
		
		return defineClass(name,out.toByteArray(),0, out.size());
	}
	
}
