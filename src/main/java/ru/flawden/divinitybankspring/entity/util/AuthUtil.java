package ru.flawden.divinitybankspring.entity.util;

import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.User;

import java.util.List;

public class AuthUtil {

    private String email;
    private String password;
    private List<User> users;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthUtil() {
    }

    public AuthUtil(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean checkAuth(UserDAO userDAO) {
        users = userDAO.index();

        for(User user: users) {
            if (user.geteMail().equals(this.email) && user.getPassword().equals(this.password)) {
                userDAO.authUser = user;
                return true;
            }
        }

        return false;
    }

}
