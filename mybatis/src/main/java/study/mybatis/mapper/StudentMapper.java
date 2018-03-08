package study.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import study.mybatis.entity.Student;

public interface StudentMapper {
	//@Select("select  * from student where id=#{id}")
	public Student getStudentById(Integer id);
	
	public int insert(Student student);
}
