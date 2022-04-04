package ru.flawden.divinitybankspring.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class LoanDAO {

    private final JdbcTemplate jdbcTemplate;

    public LoanDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Loan> loanList() {
        return jdbcTemplate.query("SELECT * FROM \"Loan\"", new BeanPropertyRowMapper<>(Loan.class));
    }

    public Loan show(int key) {
        return jdbcTemplate.query("SELECT * FROM \"Loan\" WHERE id=?", new Object[]{key}, new BeanPropertyRowMapper<>(Loan.class))
                .stream().findAny().orElse(null);
    }

    public void save(Loan loan) {
        jdbcTemplate.update("INSERT INTO \"Loan\"(issuedate, sum, interestrate, monthlypayment, creditterm) VALUES(?, ?, ?, ?, ?)",new java.sql.Date(loan.getIssueDate().getTime()) , loan.getSumm(), loan.getInterestRate(), loan.getMonthlyPayment(), loan.getCreditTerm());
    }

    public void update(String key, Loan loan) {
        jdbcTemplate.update("UPDATE \"Loan\" SET issuedate=?, sum=?, interestrate=?, monthlypayment=?, creditterm=? WHERE key=?", new java.sql.Date(loan.getIssueDate().getTime()), loan.getSumm(), loan.getInterestRate(), loan.getMonthlyPayment(), loan.getCreditTerm());
    }

    public void delete(String key) {
        jdbcTemplate.update("DELETE FROM \"Loan\" WHERE key=?", key);
    }

}
