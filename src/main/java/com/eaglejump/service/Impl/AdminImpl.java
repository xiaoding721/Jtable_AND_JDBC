package com.eaglejump.service.Impl;

import com.Util.Format;
import com.eaglejump.dao.Impl.AdminDao;
import com.eaglejump.mapping.model.BusinessMapping;
import com.eaglejump.service.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class AdminImpl implements Services {
	private final AdminDao adminDao = new AdminDao();
	
	//写入table表
	@Override
	public ArrayList<Object> updateTable(Services baseDao, ArrayList<Object> searchData) {
		Vector<String> headVector = new Vector<>();
		Vector<Vector<String>> dataVector = new Vector<>();
		headVector.add("商家名字");
		headVector.add("商铺地址");
		headVector.add("店铺介绍");
		headVector.add("起送费");
		headVector.add("配送费");
		for (Object temp : searchData) {
			Vector<String> rowVector = new Vector<>();
			BusinessMapping businessMapping = (BusinessMapping) temp;
			Format format = new Format();
			rowVector.addElement(businessMapping.getBusinessName());
			rowVector.addElement(format.ifnull(businessMapping.getBusinessAdd(), "无数据"));
			rowVector.addElement(format.ifnull(businessMapping.getBusinessEaplain(), "无数据"));
			rowVector.addElement(String.valueOf(businessMapping.getStarPrice()));
			rowVector.addElement(String.valueOf(businessMapping.getDeliveryPrice()));
			dataVector.addElement(rowVector);
		}
		ArrayList<Object> arrayList = new ArrayList<>(2);
		arrayList.add(headVector);
		arrayList.add(dataVector);
		return arrayList;
	}
	
	@Override
	public ArrayList<Object> Search(Object... value) {
		return adminDao.Search(value);
	}
	//注册管理员
	public int Insert(String adminName,char[] password) {
		return adminDao.Insert(adminName,password);
	}
	//管理员登录
	public boolean Login(String adminName,char[] password){
		return adminDao.Login(adminName,password);
	}
	//删除数据
	@Override
	public void Delete(String...name){
		adminDao.Delete(name);
	}
	
	//更新食品数据
	@Override
	public void update(Hashtable<String, String> hashtable, Services services, String foodname) {
		adminDao.update(hashtable,services,foodname);
	}
	
	//添加食品数据
	@Override
	public void Insert(Hashtable<String, String> hashtable, Services services) throws SQLException {
		adminDao.Insert(hashtable,services);
	}
}
