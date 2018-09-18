package com.zhu.hotswap;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author c6
 *	SIMPLE DEMO
 */
class HotSwapWorker implements Runnable{
	
	private final int REFRESH_TIME=2;
	private final String BASH_PATH="C:\\Users\\c6\\Java\\HotSwap\\bin\\";
	@Override
	public void run() {
		
		while(true){
			try{
				HotSwapClassLoader hotCl=new HotSwapClassLoader(BASH_PATH,"com.zhu.hotswap.my");
				Class cl=hotCl.loadClass("com.zhu.hotswap.my.Task",false);
				ITask task=(ITask)cl.newInstance();
				System.out.println(task.getClass().getClassLoader());
				task.work();
				TimeUnit.SECONDS.sleep(REFRESH_TIME);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
public class HotSwapTest {
	public static void main(String[] args) {
		/**
		 * 运行的时候更改TaskService.taskDo中的内容会自动完成替换
		 */
		new Thread(new HotSwapWorker()).start();
	}
}
