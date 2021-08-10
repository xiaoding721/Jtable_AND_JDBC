package com.eaglejump.dao.Impl;

import com.eaglejump.dao.BaseDao;
import com.eaglejump.dao.Dao;
import com.eaglejump.service.Services;

import java.util.ArrayList;
import java.util.Hashtable;

public class FoodDao extends BaseDao implements Dao {
	@Override
	public ArrayList<Object> Search(Object... value) {
		return null;
	}
	
	@Override
	public int Insert(String adminName, char[] password1) {
		return 0;
	}
	
	@Override
	public boolean Login(String adminName, char[] password1) {
		return false;
	}
	
	@Override
	public void Delete(String...name) {
	}
	
	@Override
	public void update(Hashtable<String, String> value, Services services, String user) {
		super.update(value, services, user);
	}
}
