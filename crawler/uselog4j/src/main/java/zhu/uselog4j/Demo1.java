package zhu.uselog4j;

import org.apache.log4j.Logger;

public class Demo1 {
	private static Logger logger=Logger.getLogger(Demo1.class);
    public static void main(String[] args) {
		logger.debug("debug");
		logger.info("info");
		logger.error("error");
		logger.fatal("fatal");
		logger.warn("warn");
	}
}
