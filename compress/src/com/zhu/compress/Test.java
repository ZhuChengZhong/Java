package com.zhu.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.zhu.compress.hfm.HFMCompressor;
import com.zhu.compress.lz77.LZ77Compressor;
import com.zhu.compress.lzw.LZWCompressor;

public class Test {
	public static void test(String sourceFile,String target,Compressor compressor) throws Exception{
		File file=new File(sourceFile);
		FileInputStream in=new FileInputStream(file);
		byte[]bs=new byte[(int) file.length()];
		int lenA=bs.length;
		in.read(bs);
		byte[]b=compressor.compress(bs);
		int lenB=b.length;
		byte[]sources=compressor.decompress(b);
		File fileb=new File("/home/zhu/Desktop/test-copy.java");
		FileOutputStream out=new FileOutputStream(fileb);
		out.write(sources);
		in.close();
		out.close();
		int rate=lenB*10000/lenA;
		System.out.println(rate*1.0/100);
	}
	public static void main(String[] args) throws Exception {
		//Compressor cp=new HFMCompressor();
		//Compressor cp=new LZWCompressor();
		Compressor cp=new LZ77Compressor();
		String sourceFile="/home/zhu/Desktop/test.java";
		String target="/home/zhu/Desktop/test-copy.java";
		test(sourceFile, target, cp);
	}
}
