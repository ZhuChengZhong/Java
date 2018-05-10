package com.zhu.proxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * 代理类生成类
 * @author zhu
 *
 */
public class Proxy {
	private static String ENDLINE="\n";
	//默认生成代理类的类名
	private static String DEFAULT_PROXY_NAME="Proxy$Zhu";
	public static Object newProxyInstance(CustomCLassLoader classLoader, Class interfaces,
			InvocationHandler invocationHandler) throws Exception {
		//用于存放生成的Java代码
		StringBuilder proxyContent=new StringBuilder();
		//添加包名
		proxyContent.append("package ");
		String packageName=classLoader.getClass().getPackage().getName();
		proxyContent.append(packageName).append(";").append(ENDLINE);
		//导包
		proxyContent.append("import java.lang.reflect.Method;").append(ENDLINE);
		//类名拼接
		proxyContent.append("public class "+DEFAULT_PROXY_NAME+" implements ")
		.append(interfaces.getName()).append("{").append(ENDLINE);
		//添加InvocationHandler字段
		proxyContent.append("InvocationHandler h ;").append(ENDLINE);
		//构造方法生成
		proxyContent.append("public "+DEFAULT_PROXY_NAME+"(InvocationHandler h) {").append(ENDLINE)
		.append("this.h=h ;").append(ENDLINE)
		.append("}").append(ENDLINE);
		//拼接方法
		generateMethod(proxyContent,interfaces);
		proxyContent.append("}").append(ENDLINE);
		System.out.println(proxyContent.toString());
		String javaFilePath=classLoader.getDir()+DEFAULT_PROXY_NAME+".java";
		//生成.java文件
		writeToFile(proxyContent.toString(),javaFilePath);
		//编译生成.class文件
		compile(javaFilePath);
		//生成class对象
		Class c=classLoader.findClass(DEFAULT_PROXY_NAME);
		return c.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
	}
	
	/**
	 * 将内容写入指定文件
	 * @param content
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	private static void writeToFile(String content,String fileName) throws FileNotFoundException {
		File javaFile=new File(fileName);
		PrintWriter writer=new PrintWriter(javaFile);
		writer.print(content);
		writer.flush();
		writer.close();
	}
	/**
	 * 拼接方法
	 * @param proxyContent
	 * @param interfaces
	 */
	private static void generateMethod(StringBuilder proxyContent,Class interfaces) {
		Method[] methods=interfaces.getMethods();
		for(int i=0;i<methods.length;i++) {
			Method method=methods[i];
			proxyContent.append("public ")
			.append(method.getReturnType().getName()).append(" ")
			.append(method.getName()).append("()throws Throwable{").append(ENDLINE)
			.append("Method m=")
			.append(interfaces.getName())
			.append(".class.getDeclaredMethod(\"").append(method.getName()).append("\");").append(ENDLINE)
			.append("this.h.invoke(this,m,(Object[])null);").append(ENDLINE)
			.append("}").append(ENDLINE);
			
		}
	}
	/**
	 * 编译Java代码
	 * @param proxyContent
	 * @param classFilePath
	 * @throws IOException 
	 */
	public static void compile(String javaFilePath) throws IOException {
		JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager=compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> javaFileObjects=fileManager.getJavaFileObjects(new File(javaFilePath));
		compiler.getTask(null, fileManager, null, null, null, javaFileObjects).call();
		fileManager.close();
	}
	public static void main(String[] args) throws Throwable {
		Service service=(Service)Proxy.newProxyInstance(new CustomCLassLoader("/home/zhu/my_workspace/DynamicProxy/src/com/zhu/proxy/",
				"com.zhu.proxy"), 
				Service.class,
				new MyInvocationHandler(new ServiceIMpl()));
		service.method();
	}
}
