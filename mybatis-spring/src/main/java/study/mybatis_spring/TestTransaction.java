package study.mybatis_spring;

import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import study.mybatis_spring.service.StudentService;

public class TestTransaction {
	public static void main(String[] args) {
		ApplicationContext application=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		StudentService studentService=(StudentService)application.getBean("studentService");
		studentService.findStudentById(5);
		//`DefaultSqlSessionFactory
	}
}
