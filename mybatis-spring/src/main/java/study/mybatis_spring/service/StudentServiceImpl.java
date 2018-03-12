package study.mybatis_spring.service;

import study.mybatis_spring.entity.Student;
import study.mybatis_spring.mapper.StudentMapper;

public class StudentServiceImpl implements StudentService{
	
	private StudentMapper studentMapper;
	public void findStudentById(Integer id) {
		Student student=studentMapper.getStudentById(id);
		
		System.out.println(student);
	}
	public StudentMapper getStudentMapper() {
		return studentMapper;
	}
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}
	
}
