package ru.flawden.divinitybankspring.dao;

import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.entity.DebitCard;
import ru.flawden.divinitybankspring.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class DebitCardDAO {

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

    public List<DebitCard> index(String key) {
        List<DebitCard> debitCardList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM \"DebitCard\" WHERE key=" + "\'" + key + "\'";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                DebitCard debitCard = new DebitCard();

                debitCard.setCreatedDate(resultSet.getDate("createddate"));
                debitCard.setBalance(resultSet.getDouble("balance"));
                debitCard.setCardNumber(resultSet.getString("cardnumber"));
                debitCard.setExpirationDate(resultSet.getDate("expirationdate"));
                debitCard.setCvv(resultSet.getInt("cvv"));

                debitCardList.add(debitCard);
            }
        } catch (SQLException e) {
            System.out.println("index error");
        }
        return debitCardList;
    }

    public DebitCard show(String key) {
        DebitCard debitCard = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"DebitCard\" WHERE key=?");
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                debitCard = new DebitCard();

                debitCard.setCreatedDate(resultSet.getDate("createddate"));
                debitCard.setBalance(resultSet.getDouble("balance"));
                debitCard.setCardNumber(resultSet.getString("cardnumber"));
                debitCard.setExpirationDate(resultSet.getDate("expirationdate"));
                debitCard.setCvv(resultSet.getInt("cvv"));
            }

        } catch (SQLException e) {
            System.out.println("show error");
        }
        return debitCard;
    }

    public void save(DebitCard debitCard, String key) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO \"DebitCard\"(createddate, balance, cardnumber, expirationdate, cvv, key) VALUES(?, ?, ?, ?, ?, ?)");

            preparedStatement.setDate(1, new java.sql.Date(debitCard.getCreatedDate()),
                    Calendar.getInstance());
            preparedStatement.setDouble(2, debitCard.getBalance());
            preparedStatement.setString(3, debitCard.getCardNumber());
            preparedStatement.setDate(4, new java.sql.Date(debitCard.getExpirationDate().getTime()),
                    Calendar.getInstance());
            preparedStatement.setInt(5, debitCard.getCvv());
            preparedStatement.setString(6, key);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("save error");
        }
    }

    public void update(String key, DebitCard cardFromUpdated) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE \"DebitCard\" SET createddate=?, balance=?, cardnumber=?, expirationdate=?, svv=? WHERE key=?");

            preparedStatement.setDate(1, new java.sql.Date(cardFromUpdated.getCreatedDate()),
                    Calendar.getInstance());
            preparedStatement.setDouble(2, cardFromUpdated.getBalance());
            preparedStatement.setString(3, cardFromUpdated.getCardNumber());
            preparedStatement.setDate(4, new java.sql.Date(cardFromUpdated.getExpirationDate().getTime()),
                    Calendar.getInstance());
            preparedStatement.setInt(5, cardFromUpdated.getCvv());
            preparedStatement.setString(6, key);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Update error");
        }
    }

    public void delete(String key) {
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM \"DebitCard\" WHERE key=?");

            preparedStatement.setString(1, key);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Delete error");
        }
    }

}
