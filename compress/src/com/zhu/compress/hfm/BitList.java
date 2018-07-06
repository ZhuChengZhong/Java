package com.zhu.compress.hfm;

import java.util.Arrays;
/**
 * 存放bit位的list
 * @author zhu
 *
 */
public class BitList {
	
	private int capcity;
	
	private int size;
	
	private byte[] bitkeep;
	
	private static final int MASK=7;
	
	public BitList(byte[] bs) {
		this.capcity=bs.length*8;
		size=capcity;
		bitkeep=bs;
	}
	
	public BitList(int count) {
		if(count<0) {
			throw new RuntimeException("capcity must big than 0");
		}
		capcity=(count&MASK)==0?count>>3:((count>>3)+1);
		capcity<<=3;
		bitkeep=new byte[capcity>>3];
	}
	
	/**
	 * 添加bit位
	 * @param b true 表示插入1 否则 0
	 */
	public void add(boolean b) {
		ensureCapacity(size+1);
		if(b) {
			int index=size>>3;
			int pos=7-(size&MASK);
			bitkeep[index]|=(1<<pos);
		}
		size++;
	}
	
	/**
	 * 添加一个byte的8bit
	 * @param b
	 */
	public void addByte(byte b) {
		for(int i=7;i>=0;i--) {
			if((b&(1<<i))!=0) {
				add(true);
			}else {
				add(false);
			}
		}
	}
	
	/**
	 * 从指定位置获取一个byte
	 * @param begin
	 * @return
	 */
	public byte getByte(int begin) {
		if(begin+7>=size) {
			throw new IndexOutOfBoundsException();
		}
		byte res=0;
		for(int i=begin;i<=begin+7;i++) {
			if(get(i)) {
				res|=1<<(7-i+begin);
			}
		}
		return res;
	}
	
	/**
	 * 获取指定位置的bit位是否为1
	 */
	public boolean get(int i) {
		if(i<0||i>size) {
			throw new IndexOutOfBoundsException();
		}
		int index=i>>3;
		int pos=7-(i&MASK);
		byte bt=(byte)(1<<pos);
		return (bitkeep[index]&bt)!=0;
	}
	
	/**
	 * 获取底层byte数组
	 * @return
	 */
	public byte[] toBytes() {
		int len=(size&MASK)==0?(size>>3):(size>>3)+1;
		return Arrays.copyOf(bitkeep, len);
	}
	
	/**
	 * 检查容量
	 * @param size
	 */
	private void ensureCapacity(int size) {
		if(size>=capcity) {
			grow(capcity);
		}
	}

	/**
	 * 扩容
	 * @param capcity
	 */
	private void grow(int capcity) {
		this.capcity=capcity+capcity>>1;
		this.bitkeep=Arrays.copyOf(this.bitkeep, capcity);
	}

	/**
	 * 获取元素个数
	 * @return
	 */
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return "BitList [capcity=" + capcity + ", size=" + size + ", bitkeep=" + Arrays.toString(bitkeep) + "]";
	}
	
	
}
