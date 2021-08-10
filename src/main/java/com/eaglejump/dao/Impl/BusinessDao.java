package com.eaglejump.dao.Impl;

import com.eaglejump.dao.BaseDao;
import com.eaglejump.dao.Dao;

import java.util.ArrayList;

public class BusinessDao extends BaseDao implements Dao {
	//商家注册
	@Override
	public int Insert(String businessName, char[] password1) {
		String password = cry.SHA512(new String(password1));
		String	sql = "INSERT INTO business(businessName,password) VALUE (?,?)";
		return jdbcTemplate.update(sql, businessName, password);
	}
	
	//商家登录
	@Override
	public boolean Login(String businessName,char[] password1){
		String password = cry.SHA512(new String(password1));
		String sql = "SELECT businessName,password FROM business WHERE businessName = ? AND password = ?";
		return jdbcTemplate.query(sql,mappingFactory.getFactors("BusinessMapping"),businessName,password).size()!=0;
	}
	
	//模糊查询
	@Override
	public ArrayList<Object> Search(Object...value){
		String sql = "SELECT foodName,foodExplain,foodPrice,food.businessName businessName FROM food,business WHERE( business.businessName = food.businessName AND food.businessName= ? )AND foodName LIKE ?";
		try {
			return (ArrayList<Object>) jdbcTemplate.query(sql,mappingFactory.getFactors("FoodMapping"),value);
		}catch (Exception e){
			ArrayList<Object> arrayList = new ArrayList<>();
			arrayList.add(mappingFactory.getFactors("FoodMapping"));
			return arrayList;
		}
	}
	//删除食品数据
	@Override
	public void Delete(String... name) {
		String sql = "DELETE FROM food WHERE food.businessName = ? AND foodName = ?";
		jdbcTemplate.update(sql, name[0], name[1]);
	}
}
