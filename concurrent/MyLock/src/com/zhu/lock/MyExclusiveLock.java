package com.zhu.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 通过AbstractQueuedSynchronizer实现自己的可重入的独占锁
 * @author Zhu
 *
 */
public class MyExclusiveLock {
	private static final Sync sync=new Sync();
	public void lock(){
		
		sync.lock();
	}
	public void unlock(){
		sync.release(1);
	}
	private static final class Sync extends AbstractQueuedSynchronizer{ 
		public void lock(){
			if(compareAndSetState(0, 1)){
				setExclusiveOwnerThread(Thread.currentThread());
			}else{
				acquire(1);
			}
		}
		
		/*@Override
		protected boolean tryAcquire(int acquires) {
			Thread thread=Thread.currentThread();
			int state=getState();
			if(state==0){
				if(!hasQueuedPredecessors()&&compareAndSetState(0, acquires)){
					setExclusiveOwnerThread(thread);
					return true;
				}
			}else if(thread==getExclusiveOwnerThread()){
				int newState=state+acquires;
				setState(newState);
				return true;
			}
			return false;
		}*/
		@Override
		protected boolean isHeldExclusively() {
			return Thread.currentThread()==getExclusiveOwnerThread();
		}
		@Override
		protected boolean tryAcquire(int acquires) {
			final Thread thread=Thread.currentThread();
			int state=getState();
			if(state==0){
				if(compareAndSetState(0, acquires)){
					setExclusiveOwnerThread(thread);
					return true;
				}
			}else if(thread==getExclusiveOwnerThread()){
				int newState=state+acquires;
				setState(newState);
				return true;
			}
			return false;
		}
		@Override
		protected boolean tryRelease(int acquires) {
			int state=getState();
			int newState=state-acquires;
			if(Thread.currentThread()!=getExclusiveOwnerThread()){
				throw new RuntimeException("can't release lock with out lock");
			}
			if(newState<0){
				throw new RuntimeException("no more lock to release");
			}
			if(newState==0){
				setExclusiveOwnerThread(null);
				setState(0);
				
				return true;
			}else{
				setState(newState);
			}
			return false;
		}
	}
}
