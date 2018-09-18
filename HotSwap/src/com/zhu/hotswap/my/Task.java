package com.zhu.hotswap.my;

import com.zhu.hotswap.ITask;

public class Task implements ITask{
	TaskService taskService=new TaskService();
	public void work(){
		taskService.taskDo();
		System.out.println(taskService.getClass().getClassLoader());
	}
}
