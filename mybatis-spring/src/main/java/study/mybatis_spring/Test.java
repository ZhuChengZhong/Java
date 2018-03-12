package study.mybatis_spring;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import study.mybatis_spring.entity.Student;
import study.mybatis_spring.mapper.StudentMapper;

public class Test {
	public static void main(String[] args) {
		//SqlSessionFactoryBean
		//DriverManagerDataSource
		//PooledDataSource
		//SqlSessionTemplate
		//MapperFactoryBean<T>
		//PropertiesEditor
		//MapperScannerConfigurer
		//DataSourceTransactionManager
		ApplicationContext application=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//SqlSessionFactory sqlSessionFactory=(SqlSessionFactory)application.getBean("sqlSessionFactory");
		StudentMapper studentMapper=(StudentMapper)application.getBean("studentMapper");
		Student student=studentMapper.getStudentById(5);
		System.out.println(student);
	}
}
