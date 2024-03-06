package com.example.springbootdemo.dao;

import com.example.springbootdemo.util.SearchDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        String sql = "select * from book";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book findById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from book where id = ?",
                BeanPropertyRowMapper.newInstance(Book.class),
                id);
    }

    public void save(Book book) {
        String sql = "insert into book (title, description, author, price) values(?, ?, ?, ?);";
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setString(3, book.getAuthor());
            ps.setDouble(4, book.getPrice());
            return ps;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        System.out.println(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void edit(Book book) {
        jdbcTemplate.update(
                "update book set title = ?, description = ?, author = ?, price = ? where id = ?",
                ps -> {
                    ps.setString(1, book.getTitle());
                    ps.setString(2, book.getDescription());
                    ps.setString(3, book.getAuthor());
                    ps.setDouble(4, book.getPrice());
                    ps.setInt(5, book.getId());
                }
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id = ?", id);
    }

    public List<Book> searchBook(SearchDto dto) {
        String query = "select * from book where %s ilike '%%%s%%'".formatted(dto.getProperty(), dto.getValue());
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Book.class));
    }
}
