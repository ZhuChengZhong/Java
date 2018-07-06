package com.zhu.compress.lz77;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.zhu.compress.AbstractCompressor;
import com.zhu.compress.hfm.BitList;
/**
 * LZ77压缩算法实现
 * @author zhu
 *
 */
public class LZ77Compressor extends AbstractCompressor{
	class Tuple{
		int offset;
		int len;
		public Tuple(int offset,int len) {
			this.offset=offset;
			this.len=len;
		}
	}
	/**
	 * 表示偏移量需要的位数
	 */
	private static final int MAX_OFFSET_LEN=11;
	
	/**
	 * 滑动窗口大小
	 */
	private static final int MAX_WIN_SIZE=(1<<MAX_OFFSET_LEN)-1;
	
	/**
	 * 最大匹配值(使用255不超过一字节，则长度都可用一字节表示)
	 */
	private static final int MAX_MATCH_LENGTH=255;
	
	/**
	 * 最少匹配MIN_MATCH_LENGTH个字符才进行编码
	 */
	private static final int MIN_MATCH_LENGTH=3;
	
	@Override
	public byte[] compress(byte[] sources) {
		if(sources==null || sources.length==0) {
			return sources;
		}
		int winLeft=0,winRight=-1;   //滑动窗口左右大小
		int len=sources.length;
		//用来保存压缩后的数据
		BitList bitList=new BitList(sources.length*8);
		while(winRight<len-1) {
			Tuple t=match(sources, winLeft, winRight);
			if(t.len>=MIN_MATCH_LENGTH) {
				writeTuple(bitList, t);
			}else {
				writeByte(bitList, sources[winRight+1]);
			}
			winRight+=t.len;
			winLeft=Math.max(winRight-MAX_WIN_SIZE+1,winLeft);
		}
		return bitList.toBytes();
	}
	
	/**
	 * 已编码byte前用bit位1标识
	 * @param bitList
	 * @param b
	 */
	private void writeTuple(BitList bitList,Tuple tuple) {
		bitList.add(true);
		int cur=1<<MAX_OFFSET_LEN-1;
		int offset=tuple.offset;
		while(cur>0) {
			if((offset&cur)!=0) {
				bitList.add(true);
			}else {
				bitList.add(false);
			}
			cur=cur>>1;
		}
		bitList.addByte((byte)tuple.len);
	}
	
	/**
	 * 未编码byte前用bit位0标识
	 * @param bitList
	 * @param b
	 */
	private void writeByte(BitList bitList,byte b) {
		bitList.add(false);
		bitList.addByte(b);
	}
	
	/**
	 * 寻找未编码数据 与滑动窗口内数据的最长匹配
	 * @param sources
	 * @param winLeft
	 * @param winRight
	 * @return
	 */
	private Tuple match(byte[] sources,int winLeft,int winRight) {
		int beginPos=winRight+1;
		int endPos=Math.min(beginPos+MAX_MATCH_LENGTH-1, sources.length-1);
		if(winRight==-1||endPos-beginPos<MIN_MATCH_LENGTH-1) {
			return new Tuple(0, 1);
		}
		int maxMatchOffset=0,maxMatchLen=1;
		for(int i=winLeft;i<=winRight;i++) {
			int len=0;
			for(int j=beginPos;j<=endPos;j++) {
				if(sources[i+j-beginPos]!=sources[j]||(j==endPos&&++len>0)) {
					if(len>=MIN_MATCH_LENGTH&&len>=maxMatchLen) {
						maxMatchLen=len;
						maxMatchOffset=beginPos-i;
					}
					break;
				}
				len++;
			}
		}
		Tuple tuple=new Tuple(maxMatchOffset, maxMatchLen);
		return tuple;
	}
	
	@Override
	public byte[] decompress(byte[] sources) {
		BitList bitList=new BitList(sources);
		List<Byte>res=new ArrayList<>(sources.length*2);
		int curPos=0,len=bitList.size();
		while(curPos<=len-8) {
			// 以编码数据
			if(bitList.get(curPos++)) {
				//获取偏移量
				int offset=getOffset(bitList, curPos);
				curPos+=MAX_OFFSET_LEN;
				int l=bitList.getByte(curPos);
				curPos+=8;
				decode(res, offset, l);
			}else {//原数据
				byte b=bitList.getByte(curPos);
				res.add(b);
				curPos+=8;
			}
		}
		return listToArray(res);
	}
	
	/**
	 * 从已解码数据中解码未解码数据
	 * @param res
	 * @param offset
	 * @param len
	 */
	private void decode(List<Byte>res,int offset,int len) {
		int size=res.size();
		int start=size-offset;
		for(int i=start;i<start+len;i++) {
			res.add(res.get(i));
		}
	}
	
	
	/**
	 * 获取偏移量
	 * @param bitList
	 * @param curPos
	 * @return
	 */
	private int getOffset(BitList bitList,int curPos) {
		int offset=0;
		for(int i=0;i<MAX_OFFSET_LEN;i++) {
			if(bitList.get(curPos++)) {
				offset=(offset<<1)+1;
			}else {
				offset=offset<<1;
			}
		}
		return offset;
	}
	public static void main(String[] args) {
		byte[]bts= {1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9,6,5,4,2,3,4,7,8,9,0};
		LZ77Compressor com=new LZ77Compressor();
		byte[]res=com.compress(bts);
		System.out.println(Arrays.toString(res));
		byte[]bbts=com.decompress(res);
		System.out.println(Arrays.toString(bbts));
		System.out.println(res.length*1.0/bts.length);
	}
}
