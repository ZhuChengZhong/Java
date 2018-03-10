package study.mybatis.plugin;

import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.locks.StampedLock;

import javax.sql.DataSource;

import org.apache.ibatis.executor.statement.SimpleStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.javassist.compiler.ast.Stmnt;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.log4j.Logger;
@Intercepts(@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class}))
public class LimitSelectCountPlugin implements Interceptor{
	private int limit;
	private static Logger logger=Logger.getLogger(LimitSelectCountPlugin.class);
	
	public Object intercept(Invocation invocation) throws Throwable {
		//SimpleStatementHandler
		StatementHandler statementHandler=(StatementHandler)invocation.getTarget();
		MetaObject metaobjectStatementHandler=SystemMetaObject.forObject(statementHandler);
		while(metaobjectStatementHandler.hasGetter("h")) {
			Object object=metaobjectStatementHandler.getValue("h");
			metaobjectStatementHandler=SystemMetaObject.forObject(object);
		}
		String sql=(String)metaobjectStatementHandler.getValue("delegate.boundSql.sql");
		logger.info("拦截到的sql:"+sql);
		
		sql="select * from ("+sql+") table_limit_by_zhu limit 0,"+limit;
		
		logger.info("处理后的sql:"+sql);
		
		metaobjectStatementHandler.setValue("delegate.boundsql.sql", sql);
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		logger.info("插件生成");
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		logger.info("插件属性设置");
		this.limit=Integer.parseInt((String)properties.get("limit")) ;
	}

}
