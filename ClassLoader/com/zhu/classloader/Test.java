package com.zhu.classloader;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		/*MyClassLoader classLoader=new MyClassLoader("f://a");
		Class cl=classLoader.findClass("Controller");
		Object o=cl.newInstance();
		Method m=cl.getMethod("add");
		m.invoke(o);
		System.out.println(Thread.currentThread().getContextClassLoader());*/
		
		
		/*System.out.println(Service.class.getClassLoader());
		Service service=new ServiceImpl();
		service.insert();
		
		MyClassLoader classLoader=new MyClassLoader("F:/workspace/ClassLoader/bin");
		Class cl=classLoader.findClass("com.zhu.classloader.ServiceImpl");
		Object o=cl.newInstance();
		service=(Service)o;
		service.insert();
		System.out.println(service.getClass().getClassLoader());*/
		/*System.out.println(o);
		System.out.println(o.getClass().getClassLoader());*/
		
		MyClassLoader classLoader=new MyClassLoader("F:/workspace/ClassLoader/bin");
		Class cl=classLoader.loadClass("zcom.zhu.classloader.ServiceImpl");
		Object o=cl.newInstance();
		Service service=(Service)o;
		
		MyClassLoader2 classLoader2=new MyClassLoader2("F:/workspace/ClassLoader/bin");
		Class cl2=classLoader2.loadClass("zcom.zhu.classloader.Controller");
		Object object=cl2.newInstance();
		System.out.println(object);
		System.out.println(object instanceof Controller);
		/*controller.setService(service);
		System.out.println(controller.getClass().getClassLoader());
		System.out.println(service.getClass().getClassLoader());
		controller.insert();*/
		
	}
}
