package com.eaglejump.service.Impl;

import com.Util.Format;
import com.eaglejump.dao.Impl.BusinessDao;
import com.eaglejump.mapping.model.FoodMapping;
import com.eaglejump.service.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class BusinessImpl implements Services {
	private final BusinessDao businessDao = new BusinessDao();
	
	//商家注册
	public void Insert(String businessName, char[] password) {
		businessDao.Insert(businessName, password);
	}
	
	//商家登录
	public boolean Login(String businessName, char[] password) {
		return businessDao.Login(businessName, password);
	}
	
	//模糊查询
	public ArrayList<Object> Search(Object... value) {
		return businessDao.Search(value);
	}
	
	//删除食品数据
	@Override
	public void Delete(String... name) {
		businessDao.Delete(name);
	}
	
	@Override
	public void update(Hashtable<String, String> hashtable, Services services, String foodname) {
		businessDao.update(hashtable,services,foodname);
	}
	
	@Override
	public void Insert(Hashtable<String, String> hashtable, Services services) throws SQLException {
		businessDao.Insert(hashtable,services);
	}
	
	@Override
	public ArrayList<Object> updateTable(Services baseDao, ArrayList<Object> searchData) {
		Vector<String> headVector = new Vector<>();
		Vector<Vector<String>> dataVector = new Vector<>();
		headVector.add("食品名字");
		headVector.add("食品介绍");
		headVector.add("价格");
		for (Object temp : searchData) {
			FoodMapping foodMapping = (FoodMapping) temp;
			Format format = new Format();
			Vector<String> rowVector = new Vector<>();
			rowVector.addElement(foodMapping.getFoodName());
			rowVector.addElement(format.ifnull(foodMapping.getFoodExplain(), "无数据"));
			rowVector.addElement(String.valueOf(foodMapping.getFoodPrice()));
			dataVector.addElement(rowVector);
		}
		ArrayList<Object> arrayList = new ArrayList<>(2);
		arrayList.add(headVector);
		arrayList.add(dataVector);
		return arrayList;
	}
}
