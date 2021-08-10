package com.eaglejump.dao;

import java.util.ArrayList;

public interface Dao {
	 ArrayList<Object> Search(Object...value);
	 int Insert(String adminName,char[] password1);
	 boolean Login(String adminName,char[] password1);
	 void Delete(String...name);
}
