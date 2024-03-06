package com.example.springbootdemo.dao;

import com.example.springbootdemo.domain.Item;
import com.example.springbootdemo.exception.NotFoundException;
import com.example.springbootdemo.rowmapper.ItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class ItemDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final StoreDao storeDao;

    @Autowired
    public ItemDao(JdbcTemplate jdbcTemplate, StoreDao storeDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        this.storeDao = storeDao;
    }

    public List<Item> getAll() {
        return jdbcTemplate.query("select * from item", new ItemRowMapper());
    }

    public Optional<Item> findById(int id) {
        try {
            Item item = jdbcTemplate.queryForObject(
                    "select * from item where id = ?",
                    new ItemRowMapper(),
                    id
            );
            return Optional.of(item);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void save(Item item, int store_id) {
        storeDao.findById(store_id).ifPresentOrElse((store -> {
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("name", item.getName())
                    .addValue("description", item.getDescription())
                    .addValue("price", item.getPrice())
                    .addValue("store_id", store.getId());
            KeyHolder keyHolder = simpleJdbcInsert.withTableName("item")
                    .usingColumns("name", "description", "price", "store_id")
                    .usingGeneratedKeyColumns("id")
                    .executeAndReturnKeyHolder(parameterSource);
            keyHolder.getKey().intValue();
        }), (() -> {
            throw new NotFoundException("Store with '%d' id not found".formatted(store_id));
        }));
    }

    public void edit(Item item, int store_id) {
        storeDao.findById(store_id).ifPresentOrElse((store -> {
                    jdbcTemplate.update(con -> {
                        String sql = "update item set name = ?, description = ?, price = ?, store_id = ? where id = ?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, item.getName());
                        ps.setString(2, item.getDescription());
                        ps.setDouble(3, item.getPrice());
                        ps.setInt(4, store.getId());
                        ps.setInt(5, item.getId());
                        return ps;
                    });
                }),
                (() -> {
                    throw new NotFoundException("Store with '%d' id not found".formatted(item.getId()));
                }));
    }

    public void delete(int item_id) {
        findById(item_id).ifPresentOrElse(item -> {
            jdbcTemplate.update("delete from item where id = ?", item_id);
        }, () -> {
            throw new NotFoundException("Item with id '%d'".formatted(item_id));
        });
    }

    public void setFilePath(String urlPath, int item_id) {
        findById(item_id).ifPresentOrElse((item -> {
            jdbcTemplate.update("update item set path = ? where id = ?", urlPath, item_id);
        }), () -> {
            throw new NotFoundException("Item with '%s' id not found");
        });
    }
}
