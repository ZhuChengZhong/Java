package com.zhu.lambda.custom;

public class Test {
	public static void execute(MyFunctionInterface my){
	  	String s=my.sub(1, 2, 3);
	  	System.out.println(s);
	}
	public static void main(String[] args) {
		execute((a,b,c)->a+""+b+c);
	}
}
