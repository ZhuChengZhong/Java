package com.zhu.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E>{
	private static final int DEFAULT_CAPCITY=3;
    private Semaphore emptySemaphore;
    private Semaphore fullSemaphore;
    private Lock lock=new ReentrantLock();
    private Queue<E>queue=new LinkedList<E>();
    public MyBlockingQueue(){
    	this(DEFAULT_CAPCITY);
    }
    public MyBlockingQueue(int capcity){
    	emptySemaphore=new Semaphore(0);
    	fullSemaphore=new Semaphore(capcity);
    }
    public void put(E e){
    	try {
			fullSemaphore.acquire();
			lock.lock();
	    	queue.add(e);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			lock.unlock();
			emptySemaphore.release();
		}
    }
    
    public E take(){
    	E ele=null;
    	try {
    		emptySemaphore.acquire();
			lock.lock();
			ele=queue.poll();
		} catch (InterruptedException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
			fullSemaphore.release();
		}
		return ele;
    	
    }
}
