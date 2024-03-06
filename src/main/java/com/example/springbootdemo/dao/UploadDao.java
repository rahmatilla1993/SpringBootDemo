package com.example.springbootdemo.dao;

import com.example.springbootdemo.domain.Upload;
import com.example.springbootdemo.exception.NotFoundException;
import com.example.springbootdemo.rowmapper.UploadRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.util.UUID;

@Component
public class UploadDao {

    private final JdbcTemplate jdbcTemplate;
    private final ItemDao itemDao;
    private final Path rootPath = Path.of(System.getProperty("user.home"), "/download");

    @Autowired
    public UploadDao(JdbcTemplate jdbcTemplate, ItemDao itemDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.itemDao = itemDao;
    }

    public Upload save(MultipartFile file, int item_id) {
        String contentType = file.getContentType();
        long size = file.getSize();
        String originalFilename = file.getOriginalFilename();
        String generatedName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(originalFilename);
        itemDao.findById(item_id)
                .orElseThrow(() -> new NotFoundException("Item with '%d' not found".formatted(item_id)));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into upload(original_name, generated_name, mime_type, size, item_id) values(?, ?, ?, ?, ?);";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, originalFilename);
            ps.setString(2, generatedName);
            ps.setString(3, contentType);
            ps.setLong(4, size);
            ps.setInt(5, item_id);
            return ps;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        Path resolvedPath = rootPath.resolve(generatedName);
        try {
            Files.copy(file.getInputStream(), resolvedPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        itemDao.setFilePath(resolvedPath.toString(), item_id);
        return Upload.builder()
                .originalName(originalFilename)
                .generatedName(generatedName)
                .size(size)
                .mimeType(contentType)
                .id(id)
                .uploadedPath(resolvedPath.toString())
                .build();
    }

    public Upload findByGeneratedName(String generatedName) {
        return jdbcTemplate.queryForObject(
                "select * from upload where generated_name = ?",
                new UploadRowMapper(),
                generatedName
        );
    }
}
