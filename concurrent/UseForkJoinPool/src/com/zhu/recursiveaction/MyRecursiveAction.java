package com.zhu.recursiveaction;

import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction{
	private int beginValue;
	private int endValue;
	public MyRecursiveAction(int beginValue,int endValue) {
		this.beginValue=beginValue;
		this.endValue=endValue;
	}
	@Override
	protected void compute() {
		if(endValue-beginValue>1){
			int midValue=(endValue+beginValue)/2;
			MyRecursiveAction left=new MyRecursiveAction(beginValue,midValue);
			MyRecursiveAction right=new MyRecursiveAction(midValue+1,endValue);
			this.invokeAll(left,right);
		}else{
			System.out.println("------------"+beginValue+"-----"+endValue);
		}
	}

}
