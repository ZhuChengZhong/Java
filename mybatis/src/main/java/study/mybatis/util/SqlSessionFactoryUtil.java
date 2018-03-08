package study.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public final class SqlSessionFactoryUtil {
	
	private static Logger logger=Logger.getLogger(SqlSessionFactoryUtil.class);
	
	private static SqlSessionFactory sqlSessionFactory=null;
	
	static {
		try {
			InputStream inputStream=Resources.getResourceAsStream("mybatis.xml");
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {	
			logger.error("找不到mybatis.xml文件", e);
		}
	}
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
