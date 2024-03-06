package com.example.springbootdemo.rowmapper;

import com.example.springbootdemo.domain.Store;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreRowMapper implements RowMapper<Store> {
    @Override
    public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Store.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .desc(rs.getString("description"))
                .build();
    }
}
