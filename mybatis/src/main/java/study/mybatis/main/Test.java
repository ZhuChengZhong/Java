package study.mybatis.main;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import study.mybatis.entity.Student;
import study.mybatis.mapper.StudentMapper;
import study.mybatis.util.SqlSessionFactoryUtil;

public class Test {
	public static void main(String[] args) {
		//配置文件方式
		 
		  SqlSession sqlSession=SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
			StudentMapper studentMapper=sqlSession.getMapper(StudentMapper.class);
			Student student=studentMapper.getStudentById(5);
			System.out.println(student);
			
			/*Student s1=new Student();
			s1.setAge(10);
			s1.setName("ahhh");
			s1.setNumber(1234567);
			int result=studentMapper.insert(s1);
			System.out.println("result :"+result+",id :"+s1.getId());
			sqlSession.commit();*/
			
		
		/**
		 * 编程式
		 */
/*		JdbcTransactionFactory jdbcTransactionFactory =new JdbcTransactionFactory();
		PooledDataSource dataSource=new PooledDataSource();
		dataSource.setDriver("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/students");
		dataSource.setUsername("root");
		dataSource.setPassword("121314");
		Environment environment=new Environment("development", jdbcTransactionFactory, dataSource);
		Configuration configuration=new Configuration(environment);
		configuration.getTypeAliasRegistry().registerAlias("Student", Student.class);
		configuration.addMapper(StudentMapper.class);
		SqlSession sqlSession=new SqlSessionFactoryBuilder().build(configuration).openSession();
		StudentMapper studentMapper=sqlSession.getMapper(StudentMapper.class);
		Student student=studentMapper.getStudentById(1);
		System.out.println(student);*/
		
	}
}
