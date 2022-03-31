package ru.flawden.divinitybankspring.dao;

import ru.flawden.divinitybankspring.entity.Loan;
import ru.flawden.divinitybankspring.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LoanDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/Users";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (SQLException e) {
            System.out.println("Database connection error");
        }
    }

    public List<Loan> loanList() {
        List<Loan> loans = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM \"Loan\"";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Loan loan = new Loan();

                loan.setIssueDate(resultSet.getDate("issuedate"));
                loan.setSumm(resultSet.getDouble("sum"));
                loan.setInterestRate(resultSet.getDouble("interestrate"));
                loan.setMonthlyPayment(resultSet.getDouble("monthlypayment"));
                loan.setCreditTerm(resultSet.getInt("creditterm"));

                loans.add(loan);
            }
        } catch (SQLException e) {
            System.out.println("index error");
        }
        return loans;
    }

    public void save(Loan loan) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO \"Loan\"(issuedate, sum, interestrate, monthlypayment, creditterm) VALUES(?, ?, ?, ?, ?)");

            preparedStatement.setDate(1, new java.sql.Date(loan.getIssueDate().getTime()),
                    Calendar.getInstance());
            preparedStatement.setDouble(2, loan.getSumm());
            preparedStatement.setDouble(3, loan.getInterestRate());
            preparedStatement.setDouble(4, loan.getMonthlyPayment());
            preparedStatement.setInt(5, loan.getCreditTerm());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("save error");
        }
    }

    public void update(String key, Loan loanFromUpdated) {
//        authUser = userForUpdate;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE \"Loan\" SET issuedate=?, sum=?, interestrate=?, monthlypayment=?, creditterm=? WHERE key=?");

            preparedStatement.setDate(1, new java.sql.Date(loanFromUpdated.getIssueDate().getTime()),
                    Calendar.getInstance());
            preparedStatement.setDouble(2, loanFromUpdated.getSumm());
            preparedStatement.setDouble(3, loanFromUpdated.getInterestRate());
            preparedStatement.setDouble(4, loanFromUpdated.getMonthlyPayment());
            preparedStatement.setInt(5, loanFromUpdated.getCreditTerm());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Update error");
        }
    }

    public void delete(String key) {
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM \"Loan\" WHERE key=?");

            preparedStatement.setString(1, key);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Delete error");
        }
    }

}
