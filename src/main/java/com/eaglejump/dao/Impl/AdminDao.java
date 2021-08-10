package com.eaglejump.dao.Impl;

import com.eaglejump.dao.BaseDao;
import com.eaglejump.dao.Dao;

import java.util.ArrayList;
public class AdminDao extends BaseDao implements Dao {
	//模糊查询
	@Override
	public ArrayList<Object> Search(Object...value){
		String sql = "SELECT * FROM business WHERE businessName LIKE ?";
		return (ArrayList<Object>) jdbcTemplate.query(sql,mappingFactory.getFactors("BusinessMapping"),value);
	}
	//注册管理员
	@Override
	public int Insert(String adminName,char[] password1) {
		String sql = "INSERT INTO admin (adminName,password) VALUE (?,?);";
		String password = cry.SHA512(new String(password1));
		return jdbcTemplate.update(sql,adminName,password);
	}
	
	//管理员登录
	@Override
	public boolean Login(String adminName,char[] password1){
		String password = cry.SHA512(new String(password1));
		String sql = "SELECT adminName,password FROM admin WHERE adminName = ? AND password = ?";
		return jdbcTemplate.query(sql,mappingFactory.getFactors("AdminMapping"),adminName,password).size()!=0;
	}
	//删除数据
	@Override
	public void Delete(String...name){
		String sql = "DELETE FROM business WHERE businessName = ?";
		jdbcTemplate.update(sql,name[0]);
	}
}
