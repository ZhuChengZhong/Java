package study.mybatis_spring.mapper;

import org.springframework.stereotype.Repository;

import study.mybatis_spring.entity.Student;
@Repository
public interface StudentMapper {
	//@Select("select  * from student where id=#{id}")
	public Student getStudentById(Integer id);
	
	public int insert(Student student);
}
