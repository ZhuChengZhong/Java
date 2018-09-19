package com.zhu.hotswap.agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

public class HotCheckAndSwapTask implements Runnable{
	
	private static final String ROOT_PATH="hotfiles\\";
	
	private Instrumentation inst;
	
	public HotCheckAndSwapTask(Instrumentation inst) {
		this.inst=inst;
	}
	
	@Override
	public void run() {
		System.out.println("[hot swap] begin");
		List<File>classFiles=getAllClassFile(new File(ROOT_PATH));
		List<ClassDefinition>classDfList=new ArrayList<>();
		for(File file:classFiles){
			String className=getClassNameFromFile(file);
			try {
				Class cl=Class.forName(className);
				byte[]classBytes=getByteFromFile(file);
				ClassDefinition classDf=new ClassDefinition(cl, classBytes);
				classDfList.add(classDf);
			} catch (ClassNotFoundException e) {
				System.out.println("[hot swap] class not found "+className);
			}
		}
		try {
			inst.redefineClasses(classDfList.toArray(new ClassDefinition[]{}));
		} catch (Exception e) {
			System.out.println("[hot swap] fail "+e);
		}
		System.out.println("[hot swap] over "+classFiles);
		for(File file:classFiles){
			file.delete();
		}
	}
	
	private static List<File> getAllClassFile(File dirFile){
		List<File> classFiles=new ArrayList<>();
		if(!dirFile.exists()||!dirFile.isDirectory()){
			System.out.println("file not exists or is not dir");
			return classFiles;
		}
		File[] files=dirFile.listFiles();
		for(File f:files){
			if(f.isFile()&&f.getPath().endsWith(".class")){
				System.out.println("[hot swap] find new class file "+f.getPath());
				classFiles.add(f);
			}else if(f.isDirectory()){
				classFiles.addAll(getAllClassFile(f));
			}
		}
		return classFiles;
	}
	
	private static String getClassNameFromFile(File classFile){
		String path=classFile.getPath();
		return path.substring(ROOT_PATH.length(), path.length()-6).replace('\\', '.');
	}
	
	private static byte[] getByteFromFile(File file){
		try{
			int fileLen=(int)file.length();
			byte[] classBytes=new byte[(int)file.length()];
			FileInputStream in=new FileInputStream(file);
			in.read(classBytes, 0, fileLen);
			in.close();
			return classBytes;
		}catch(Exception e){
			
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(getAllClassFile(new File("f:/hotswap/hotfiles/")));
	}
}
