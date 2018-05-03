package com.zhu.test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUMap<K,V> extends LinkedHashMap<K, V>{
	private int bufferSize;
	public LRUMap(int bufferSize) {
		super(bufferSize,0.75f, true);
		this.bufferSize=bufferSize;
	}
	@Override
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		if(this.size()>this.bufferSize) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		LRUMap<String, Integer>map=new LRUMap<>(3);
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		System.out.println(map);
		map.get("a");
		System.out.println(map);
		map.put("d", 4);
		System.out.println(map);
	}
}
