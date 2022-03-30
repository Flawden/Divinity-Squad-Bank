package ru.flawden.divinitybankspring.dao;

import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.entity.User;

import java.sql.*;
import java.util.*;

@Component
public class UserDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/Users";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";
    private static Connection connection;

    public Set<String> cardNumbers;
    public User authUser;

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

    {
        cardNumbers = new HashSet<>();
    }

    public List<User> index() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM \"User\"";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.seteMail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("index error");
        }
        return users;
    }

    public User show(int id) {
        //return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"User\" WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new User();

                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.seteMail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("show error");
        }
        return user;
    }

    public void save(User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO \"User\"(firstname, lastname, email, password) VALUES(?, ?, ?, ?)");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.geteMail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("save error");
        }
    }

    public void update(int id, User userFromUpdated) {
//        User userForUpdate = show(id);
//
//        userForUpdate.setFirstName(userFromUpdated.getFirstName());
//        userForUpdate.setLastName(userFromUpdated.getLastName());
//        userForUpdate.seteMail(userFromUpdated.geteMail());
//        userForUpdate.setPassword(userFromUpdated.getPassword());
//        authUser = userForUpdate;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE \"User\" SET firstname=?, lastname=?, email=?, password=? WHERE id=?");

            preparedStatement.setString(1, userFromUpdated.getFirstName());
            preparedStatement.setString(2, userFromUpdated.getLastName());
            preparedStatement.setString(3, userFromUpdated.geteMail());
            preparedStatement.setString(4, userFromUpdated.getPassword());
            preparedStatement.setInt(5, userFromUpdated.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Update error");
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM \"User\" WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Delete error");
        }
    }

}
