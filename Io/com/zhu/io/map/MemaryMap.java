package com.zhu.io.map;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

public class MemaryMap {
	public static void main(String[] args) throws IOException {
		
		String fileName="f://UseStreamReader.java";
		File file=new File(fileName);
		int length=(int)file.length();
		int bufferSize=1024;
		int size=(int)length/bufferSize+1;
		MappedByteBuffer[] buffers=new MappedByteBuffer[size];
		RandomAccessFile rfile=new RandomAccessFile(fileName,"r");
		int remaining=length;
		for (int i=0;i<size;i++) {
			buffers[i]=rfile.getChannel().map(MapMode.READ_ONLY,bufferSize*i,Math.min(remaining, bufferSize));
			remaining=length-bufferSize*i;
			
		}
		for(int i=0;i<size;i++) {
			//buffers[i].flip();
			byte []bytes=new byte[1024];
			buffers[i].get(bytes, 0, buffers[i].limit());
			System.out.println(new String(bytes, 0, buffers[i].limit(),"utf-8"));
			
		}
	}
}
