package com.eaglejump.mapping.model;

import com.eaglejump.mapping.EntityMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessMapping implements EntityMapping {
    private String businessName;
    private String businessAdd;
    private String businessEaplain;
    private double starPrice;
    private double deliveryPrice;
    
    private static BusinessMapping businessMapping;
    private BusinessMapping(){}
    
    public static BusinessMapping getBusinessMapping() {
        if(businessMapping == null){
            businessMapping = new BusinessMapping();
        }
        return businessMapping;
    }
    
    @Override
    public Object mapping(ResultSet rs) throws SQLException {
        BusinessMapping businessMapping;
        try {
            businessMapping = new BusinessMapping();
            businessMapping.businessAdd = rs.getString("businessAddress");
            businessMapping.businessEaplain = rs.getString("businessExplain");
            businessMapping.starPrice = rs.getDouble("starPrice");
            businessMapping.deliveryPrice = rs.getDouble("deliveryPrice");
            businessMapping.businessName = rs.getString("businessName");
        }catch (SQLException e){
            businessMapping = new BusinessMapping();
            businessMapping.businessName = rs.getString("businessName");
        }
        return businessMapping;
    }
    
    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessAdd() {
        return businessAdd;
    }

    public String getBusinessEaplain() {
        return businessEaplain;
    }

    public double getStarPrice() {
        return starPrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }
}
