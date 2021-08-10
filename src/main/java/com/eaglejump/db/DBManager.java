package com.eaglejump.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import com.Util.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DBManager {
	
	private Properties prop;
	private PropertyUtil propertyUtil;
	private static DBManager dbManager = null;
	private final Log logger = LogFactory.getLog(DBManager.class);

	private DBManager(){
	}

	public static DBManager getInstance() {
		if (dbManager == null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}

	/**
	 * @return
	 * 与数据库进行连接
	 */
	public Connection getConnection() {
		if (propertyUtil == null) {
			propertyUtil = PropertyUtil.getInstance();
			if (prop == null) {
				String path = "src/datainfo.properties";
				prop = propertyUtil.getProperty(path);
				if (logger.isInfoEnabled()) {
					logger.info("读取数据库配置文件：	" + path);
				}
			}
		}
		Connection conn = null;
		try {
			Class.forName(prop.getProperty("DRIVER"));
			conn = DriverManager.getConnection(prop.getProperty("DBURL"),prop.getProperty("USERNAME"),prop.getProperty("USERPWD"));
			conn.setAutoCommit(false);//先这样简单自动提交，以后再业务层管理事务，就需要设置为false了
			if (logger.isInfoEnabled()) {
				logger.info("读取数据库配置文件成功！");
			}
		} catch (ClassNotFoundException e) {
			if (logger.isErrorEnabled()) {
				logger.error("找不到数据库驱动！");
			}
		} catch (SQLException e) {
			if (logger.isErrorEnabled()) {
				logger.error("连接失败！");
			}
		}
		return conn;
	}
}
