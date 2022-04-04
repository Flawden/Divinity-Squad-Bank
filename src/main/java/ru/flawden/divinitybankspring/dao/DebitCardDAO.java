package ru.flawden.divinitybankspring.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.dao.mapper.DebitCardMapper;
import ru.flawden.divinitybankspring.entity.DebitCard;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class DebitCardDAO {

    private final JdbcTemplate jdbcTemplate;

    public DebitCardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DebitCard> index(String key) {
        return jdbcTemplate.query("SELECT * FROM \"DebitCard\" WHERE key=?", new Object[]{key}, new DebitCardMapper());
    }

    public DebitCard show(String key) {
        return jdbcTemplate.query("SELECT * FROM \"DebitCard\" WHERE key=?", new Object[]{key}, new DebitCardMapper())
                .stream().findAny().orElse(null);
    }

    public void save(DebitCard card, String key) {
        jdbcTemplate.update("INSERT INTO \"DebitCard\"(createddate, balance, cardnumber, expirationdate, cvv, key) VALUES(?, ?, ?, ?, ?, ?)", new java.sql.Date(card.getCreatedDate()), card.getBalance(), card.getCardNumber(), new java.sql.Date(card.getExpirationDate().getTime()), card.getCvv(), key);
    }

    public void update(String key, DebitCard card) {
        jdbcTemplate.update("UPDATE \"DebitCard\" SET createddate=?, balance=?, cardnumber=?, expirationdate=?, svv=? WHERE key=?", new java.sql.Date(card.getCreatedDate()), card.getBalance(), card.getCardNumber(), new java.sql.Date(card.getExpirationDate().getTime()), card.getCvv(), key);
    }

    public void delete(String key) {
        jdbcTemplate.update("DELETE FROM \"DebitCard\" WHERE key=?", key);
    }

}
