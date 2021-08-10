package com.eaglejump.mapping;

import com.eaglejump.mapping.model.AdminMapping;
import com.eaglejump.mapping.model.BusinessMapping;
import com.eaglejump.mapping.model.FoodMapping;

import java.util.Hashtable;
import java.util.Map;

public class MappingFactory{
    private MappingFactory() {
    }
    private static MappingFactory mappingFactory;
    
    
    private final Map<String, EntityMapping> factors = new Hashtable<>();
    public static MappingFactory getMappingFactory() {
        if (mappingFactory == null) {
            mappingFactory = new MappingFactory();
            mappingFactory.factors.put("AdminMapping", AdminMapping.getAdminMapping());
            mappingFactory.factors.put("BusinessMapping",BusinessMapping.getBusinessMapping());
            mappingFactory.factors.put("FoodMapping",FoodMapping.getFoodMapping());
        }
        return mappingFactory;
    }
    
    public EntityMapping getFactors(String key) {
        return this.factors.get(key);
    }
}
