package com.eaglejump.mapping.model;

import com.eaglejump.mapping.EntityMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodMapping implements EntityMapping {
	
	private String foodName;
	private String foodExplain;
	private double foodPrice;
	
	private static FoodMapping foodMapping;
	private FoodMapping(){}
	
	public static FoodMapping getFoodMapping() {
		if(foodMapping == null){
			foodMapping = new FoodMapping();
		}
		return foodMapping;
	}
	
	@Override
	public Object mapping(ResultSet rs) throws SQLException {
		FoodMapping foodMapping =new FoodMapping();
		foodMapping.foodName = rs.getString("foodName");
		foodMapping.foodExplain = rs.getString("foodExplain");
		foodMapping.foodPrice = rs.getDouble("foodPrice");
		return foodMapping;
	}
	
	public String getFoodName() {
		return foodName;
	}
	
	public String getFoodExplain() {
		return foodExplain;
	}
	
	public double getFoodPrice() {
		return foodPrice;
	}
}
