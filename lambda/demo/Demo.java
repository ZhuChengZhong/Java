package com.zhu.lambda.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Demo {
	
	public static void main(String[] args) {
		//1
		/*new Thread(()->System.out.println("sad")).start();
		Runnable r=() -> System.out.println("Hello Lambda Expressions");
		new Thread(r).start();*/
		
		//2
		List <Integer>list=new ArrayList<Integer>();
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		Comparator<Integer>comparator=(x,y)->x>y?1:(x==y?0:-1);
		list.sort(comparator);
		
		list.forEach(number->System.out.println(number));
		
		
	}
}
