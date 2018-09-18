package com.zhu.hotswap;

import java.io.File;
import java.io.RandomAccessFile;

public class HotSwapClassLoader extends ClassLoader {

	private String basePath;

	private String startWith;

	public HotSwapClassLoader(String basePath,String startWith) {
		this.basePath = basePath;
		this.startWith=startWith;
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		Class cls = null;
		try {
			cls = findLoadedClass(name);
			if (cls == null && name.startsWith(startWith)) {
				String path = basePath + name.replace(".", File.separator) + ".class";
				RandomAccessFile file = new RandomAccessFile(path, "r");
				int len = (int) file.length();
				byte[] classBytes = new byte[len];
				file.read(classBytes, 0, len);
				cls = defineClass(name, classBytes, 0, len);
			} else {
				cls = getSystemClassLoader().loadClass(name);
			}
			if (cls == null) {
				throw new ClassNotFoundException(name);
			}
			if (resolve) {
				resolveClass(cls);
			}
			return cls;
		} catch (Exception e) {
			throw new ClassNotFoundException(name);
		}
	}

	@Override
	public String toString() {
		return "HotSwapClassLoader by zhu "+hashCode();
	}
	
}
