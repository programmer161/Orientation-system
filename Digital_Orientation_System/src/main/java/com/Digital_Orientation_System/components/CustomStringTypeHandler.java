package com.Digital_Orientation_System.components;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.StringTypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomStringTypeHandler extends StringTypeHandler {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, StringUtils.trim(parameter));
    }
}