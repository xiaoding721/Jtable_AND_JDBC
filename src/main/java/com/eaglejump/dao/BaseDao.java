package com.eaglejump.dao;

import com.Util.Cry;
import com.eaglejump.db.DBManager;
import com.eaglejump.db.JdbcTemplate;
import com.eaglejump.mapping.MappingFactory;
import com.eaglejump.service.Impl.AdminImpl;
import com.eaglejump.service.Impl.BusinessImpl;
import com.eaglejump.service.Services;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

public  class BaseDao {
	
	protected JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();// JDBC模板
	protected MappingFactory mappingFactory = MappingFactory.getMappingFactory();// mapping工厂实例
	protected Log logger = LogFactory.getLog("DAOLogger");
	protected Cry cry = new Cry();
	
	protected BaseDao(){
		DBManager dbManager =DBManager.getInstance();
		Connection connection = dbManager.getConnection();
		this.setConnection(connection);
	}
	
	public void update(Hashtable<String, String> value, Services services, String user){
		int count = 0;
		Object [] temp = new Object[value.size()];
		StringBuilder sql,sqlend;
		if(services instanceof AdminImpl) {
			try{
				value.put("password",cry.SHA512(value.get("password")));
			}catch (Exception ignored){}
			sql = new StringBuilder("UPDATE business SET ");
			sqlend = new StringBuilder(" WHERE businessName = ");
		}else {
			sql = new StringBuilder("UPDATE food SET ");
			sqlend = new StringBuilder("WHERE foodName = ");
		}
		Enumeration<String> keys = value.keys();
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			if(!value.get(key).equals("")) {
				sql.append(key).append("=").append("?, ");
				temp[count] = value.get(key);
				count++;
			}
		}
		Object [] values = new Object[count];
		System.arraycopy(temp, 0, values, 0, count);
		sql.deleteCharAt (sql.length()-2);
		sql.append(sqlend).append("'").append(user).append("'");
		jdbcTemplate.update(String.valueOf(sql),values);
	}
	
	//动态可变sql
	public void Insert(Hashtable<String,String> value, Services services)throws SQLException {
		int count = 0;
		Object [] temp = new Object[value.size()];
		StringBuilder sql;
		if(services instanceof AdminImpl) {
			value.put("password",cry.SHA512(value.get("password")));
			sql = new StringBuilder("INSERT INTO business(");
		}else {
			sql = new StringBuilder("INSERT INTO food(");
		}
		Enumeration<String> keys = value.keys();
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			if(!value.get(key).equals("")) {
				sql.append(key).append(",");
				temp[count] = value.get(key);
				count++;
			}
		}
		Object [] values = new Object[count];
		System.arraycopy(temp, 0, values, 0, count);
		sql.deleteCharAt (sql.length()-1);
		sql.append(") VALUE (");
		for (;count!=0;count--){
			sql.append("?,");
		}
		sql.deleteCharAt (sql.length()-1);
		sql.append(")");
		jdbcTemplate.update(String.valueOf(sql),values);
	}
	
	/**
	 * 注入数据库连接类
	 *
	 * @param connection  数据库链接
	 */
	protected void setConnection(Connection connection) {
		jdbcTemplate.setConnection(connection);
	}
}
