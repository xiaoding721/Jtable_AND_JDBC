package com.eaglejump.service.Impl;

import com.eaglejump.dao.Impl.FoodDao;
import com.eaglejump.service.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class FoodImpl implements Services {
	private final FoodDao foodDao =new FoodDao();
	
	@Override
	public ArrayList<Object> Search(Object... value) {
		return foodDao.Search(value);
	}
	
	@Override
	public ArrayList<Object> updateTable(Services baseDao, ArrayList<Object> searchData) {
		return null;
	}
	
	@Override
	public void Delete(String...name) {
		foodDao.Delete(name);
	}
	
	@Override
	public void update(Hashtable<String, String> value, Services services, String user) {
		foodDao.update(value,services,user);
	}
	
	@Override
	public void Insert(Hashtable<String, String> hashtable, Services services) {
		try {
			foodDao.Insert(hashtable,services);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
