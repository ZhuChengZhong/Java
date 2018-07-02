package com.zhu.testexception;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//��ʹ��invokeAnyʱ  ֻ����һ�����ִ���������Ľ�������������׳��쳣���ᴫ�ݵ����̣߳���Ҫ���׳��쳣���߳����Լ�����
//����׳��쳣��������ִ���꣬���ᱻ����ֵ��ֱ����һ������ִ�е�����Ȼ�󷵻أ����һֱ�����û�з���ֵ���׳��쳣���׳����쳣�������ֵ��쳣
public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es=new ThreadPoolExecutor(2,3,5,TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());
		/*List<Future<String>>list=es.invokeAll(Arrays.asList(new MyCallable()));*/
		String result=es.invokeAny(Arrays.asList(new MyCallableA(),new MyCallable()));
		System.out.println(result);
		es.shutdown();
	}
}