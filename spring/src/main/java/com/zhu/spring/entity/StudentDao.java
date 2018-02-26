package com.zhu.spring.entity;

import org.springframework.stereotype.Component;

@Component
public class StudentDao {
	public void insert(int id){
		System.out.println("插入学生 ："+id);
	}
}
