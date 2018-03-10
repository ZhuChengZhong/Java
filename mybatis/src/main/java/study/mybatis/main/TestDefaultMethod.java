package study.mybatis.main;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.SimpleStatementHandler;

public class TestDefaultMethod {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Class c=TestInterface.class;
		Method method=c.getMethod("fun");
		System.out.println(method.getModifiers());
		System.out.println(method.getDeclaringClass().isInterface());
		System.out.println(isDefaultMethod(method));
		System.out.println(Modifier.PUBLIC);
		
	}
	  private static boolean isDefaultMethod(Method method) {
		    return ((method.getModifiers()
		        & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC)
		        && method.getDeclaringClass().isInterface();
	  }
}
