package com.zhu.hotswap;

import com.zhu.hotswap.my.Task;

public class ClassLoaderTest {
	public static void main(String[] args) throws Exception {
	/*	try {
			Class cl = ClassLoader.getSystemClassLoader().loadClass("com.zhu.hotswap.Task");
			Object o = cl.newInstance();
			Method m=cl.getMethod("work");
			m.invoke(o);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		String basePath="C:\\Users\\c6\\Java\\workspace\\HotSwap\\bin\\";
		HotSwapClassLoader hotCl=new HotSwapClassLoader(basePath,"com.zhu.hotswap.my");
		Class cl=hotCl.loadClass("com.zhu.hotswap.my.Task",false);
		ITask task=(ITask)cl.newInstance();
		System.out.println(task.getClass().getClassLoader());
		task.work();
	}
}
