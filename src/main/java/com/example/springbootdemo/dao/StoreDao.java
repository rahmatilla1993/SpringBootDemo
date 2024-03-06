package com.example.springbootdemo.dao;

import com.example.springbootdemo.domain.Store;
import com.example.springbootdemo.exception.NotFoundException;
import com.example.springbootdemo.rowmapper.StoreRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class StoreDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StoreDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Store> getAll() {
        return jdbcTemplate.query("select * from store;", new StoreRowMapper());
    }

    public Optional<Store> findById(int id) {
        try {
            Store store = jdbcTemplate.queryForObject(
                    "select * from store where id = :id;",
                    Map.of("id", id),
                    new StoreRowMapper()
            );
            return Optional.of(store);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public int save(Store store) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("name", store.getName())
                .addValue("description", store.getDesc());
        jdbcTemplate.update(
                "insert into store(name, description) values (:name, :description);",
                paramSource,
                keyHolder,
                new String[]{"id"}
        );
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public void edit(Store store, int id) {
        findById(id).ifPresentOrElse((storeFromDb -> {
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("name", store.getName())
                    .addValue("description", store.getDesc())
                    .addValue("id", id);
            jdbcTemplate.update(
                    "update store set name = :name, description = :description where id = :id;",
                    parameterSource
            );
        }), (() -> {
            throw new NotFoundException("Store with '%d' id not found".formatted(id));
        }));
    }

    public void delete(int id) {
        findById(id).ifPresentOrElse((store ->
                        jdbcTemplate.update("delete from store where id = :id;", Map.of("id", id))),
                (() -> {
                    throw new NotFoundException("Store with '%d' id not found".formatted(id));
                })
        );
    }
}
