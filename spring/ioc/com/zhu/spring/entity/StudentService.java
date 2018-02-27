package com.zhu.spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	public void insert(int id){
		studentDao.insert(id);
	}
}
