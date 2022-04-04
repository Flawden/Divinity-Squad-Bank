package ru.flawden.divinitybankspring.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.entity.User;

import java.sql.*;
import java.util.*;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;
    public User authUser;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index() {
        return jdbcTemplate.query("SELECT * FROM \"User\"", new BeanPropertyRowMapper<>(User.class));
    }

    public User show(int id) {
        return jdbcTemplate.query("SELECT * FROM \"User\" WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO \"User\"(firstname, lastname, email, password, registrationdate) VALUES(?, ?, ?, ?, ?)", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), new java.sql.Date(user.getRegistrationDate().getTime()));
    }

    public void update(int id, User user) {
        jdbcTemplate.update("UPDATE \"User\" SET firstname=?, lastname=?, email=?, password=? WHERE id=?", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM \"User\" WHERE id=?", id);
    }

}
