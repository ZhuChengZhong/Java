package com.zhu.compress.lzw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.zhu.compress.AbstractCompressor;

public class LZWCompressor extends AbstractCompressor{
	
	public byte[] compress(byte[] sources) {
		if(sources==null||sources.length==0) {
			return sources;
		}
		int len=sources.length;
		int start=0;
		int[] end=new int[1];
		byte[] res = null;
		while(start<len) {
			byte[]bts=compress(sources, start, end);
			if(res==null) {
				res=bts;
			}else {
				res=concat(res, bts);
			}
			start=end[0]+1;
		}
		return res;
	}
	
	private  byte[] compress(byte[] sources,int start,int[]end) {
		HashMap<String,Byte>table=new HashMap<>();
		StringBuilder suffix=new StringBuilder();
		StringBuilder prefix=new StringBuilder();
		byte identifier=-127;   //0X81-0XFF
		suffix.append((char)sources[start]);
		int len=sources.length;
		int index=start;
		int i=start+1;
		while(true) {
			//标记使用完
			if(identifier==0||i==len) {
				break;
			}
			prefix.append(suffix);
			suffix.delete(0, suffix.length());
			suffix.append((char)sources[i]);
			String key=prefix.toString()+suffix.toString();
			if(!table.containsKey(key)) {
				if(prefix.length()==1) {
					sources[index++]=sources[i-1];
				}else {
					sources[index++]=table.get(prefix.toString());
				}
				prefix.delete(0,prefix.length());
				table.put(key, identifier++);
			}
			i++;
		}
		prefix.append(suffix);
		if(prefix.length()==1) {
			sources[index++]=sources[i-1];
		}else {
			sources[index++]=table.get(prefix.toString());
		}
		end[0]=i-1;
		byte[] arr=new byte[index-start+1];
		System.arraycopy(sources, start, arr, 0, index-start);
		arr[arr.length-1]=0;
		return arr;
	}
	
	
	public  byte[] decompress(byte[]sources) {
		if(sources==null||sources.length==0) {
			return sources;
		}
		int start=0;
		int end=0;
		byte[]res=null;
		while(end<sources.length) {
			if(sources[end]==0||end==sources.length-1) {
				byte[]arr=decompress(sources, start, end-1);
				if(res==null) {
					res=arr;
				}else {
					res=concat(res, arr);
				}
				start=end+1;
			}
			end++;
		}
		return res;
	}
	
	public  byte[] decompress(byte[]sources,int start,int end) {
		byte identifier=-127;
		HashMap<Byte,String>table=new HashMap<>();
		StringBuilder suffix=new StringBuilder();
		StringBuilder prefix=new StringBuilder();
		StringBuilder res=new StringBuilder();
		suffix.append((char)sources[start]);
		for(int i=start+1;i<=end;i++) {
			prefix.delete(0, prefix.length());
			prefix.append(suffix);
			suffix.delete(0, suffix.length());
			res.append(prefix);
			byte b=sources[i];
			if(b<0) {
				String deS=table.get(b);
				if(deS==null) {
					byte pre=sources[i-1];
					if(pre<0) {
						String preS=table.get(pre);
						suffix.append(preS).append(preS.charAt(0));
					}else {
						suffix.append((char)sources[i-1]).append((char)sources[i-1]);
					}
				}else {
					suffix.append(deS);
				}
			}else {
				suffix.append((char)b);
			}
			String value=prefix.toString()+suffix.substring(0, 1);
			table.put(identifier++, value);
		}
		res.append(suffix);
		return res.toString().getBytes();
	}
}
