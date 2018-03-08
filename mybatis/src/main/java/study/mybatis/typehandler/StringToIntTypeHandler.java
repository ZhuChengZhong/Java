package study.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

public class StringToIntTypeHandler implements TypeHandler<Integer>{

	private static Logger logger=Logger.getLogger(StringToIntTypeHandler.class);
	
	public void setParameter(PreparedStatement ps, int i, Integer parameter,
			JdbcType jdbcType) throws SQLException {
		logger.info("使用setParameter方法转变类型");
		ps.setString(i, parameter+"");
	}

	public Integer getResult(ResultSet rs, String columnName)
			throws SQLException {
		logger.info("使用getResult方法转变类型");
		return Integer.parseInt(rs.getString(columnName));
	}

	public Integer getResult(ResultSet rs, int columnIndex) throws SQLException {
		logger.info("使用setParameter方法转变类型");
		return Integer.parseInt(rs.getString(columnIndex));
	}

	public Integer getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		logger.info("使用setParameter方法转变类型");
		return Integer.parseInt(cs.getString(columnIndex));
	}

	
}
