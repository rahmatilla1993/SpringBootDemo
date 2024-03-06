package com.example.springbootdemo.rowmapper;

import com.example.springbootdemo.domain.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Item.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getDouble("price"))
                .path(rs.getString("path"))
                .build();
    }
}
