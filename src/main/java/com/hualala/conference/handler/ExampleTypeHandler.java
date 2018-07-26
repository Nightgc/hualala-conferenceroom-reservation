package com.hualala.conference.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@MappedJdbcTypes(JdbcType.DATE)
public class ExampleTypeHandler extends BaseTypeHandler<Date> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        preparedStatement.setDate(i, (java.sql.Date) date);
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getDate(s);
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getDate(i);
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getDate(i) ;
    }
}
