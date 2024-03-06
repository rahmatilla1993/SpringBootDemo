package com.example.springbootdemo.rowmapper;

import com.example.springbootdemo.domain.Upload;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UploadRowMapper implements RowMapper<Upload> {
    @Override
    public Upload mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Upload.builder()
                .id(rs.getInt("id"))
                .originalName(rs.getString("original_name"))
                .generatedName(rs.getString("generated_name"))
                .mimeType(rs.getString("mime_type"))
                .size(rs.getLong("size"))
                .build();
    }
}
