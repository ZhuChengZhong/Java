package com.zhu.io.turn;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class UseStreamReader {
	public static void main(String[] args) throws Exception {
	
			FileReader reader=new FileReader("f://UseStreamReader.java");
			System.out.println(reader.getEncoding());
			
			char [] cbuf=new char[1024];
			StringBuffer sb=new StringBuffer();
			int len;
			while((len=reader.read(cbuf))>0) {
				sb.append(cbuf,0,len);
			}
			//汉字
		System.out.println(sb.toString());
		
	}
}
