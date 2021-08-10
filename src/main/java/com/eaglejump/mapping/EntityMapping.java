package com.eaglejump.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapping{
    Object mapping(ResultSet rs) throws SQLException;
}
