package com.zhu.shutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestShutDown {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es=Executors.newFixedThreadPool(2);
		es.execute(new MyRunnable());
		Thread.sleep(7000);
		es.shutdownNow();
	}
}
