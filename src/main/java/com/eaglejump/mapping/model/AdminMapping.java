package com.eaglejump.mapping.model;

import com.eaglejump.mapping.EntityMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapping implements EntityMapping {
    private String adminName;
    private static AdminMapping adminMapping;
    private AdminMapping(){}
    
    public static AdminMapping getAdminMapping() {
        if(adminMapping == null){
            adminMapping = new AdminMapping();
        }
        return adminMapping;
    }
    
    @Override
    public Object mapping(ResultSet rs) throws SQLException {
        AdminMapping adminMapping = new AdminMapping();
        adminMapping.adminName = rs.getString("adminName");
        return adminMapping;
    }


    public String getAdminName() {
        return adminName;
    }
}
