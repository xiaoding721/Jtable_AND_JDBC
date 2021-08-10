package com.eaglejump.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;


public  interface Services {
      ArrayList<Object> Search(Object... value);
      ArrayList<Object> updateTable(Services services, ArrayList<Object> searchData);
      void Delete(String...name);
      void update(Hashtable<String, String> hashtable, Services services, String foodname);
      void Insert(Hashtable<String, String> hashtable, Services services)throws SQLException;
}
