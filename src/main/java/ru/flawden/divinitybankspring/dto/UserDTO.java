package ru.flawden.divinitybankspring.dto;

import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.UserEntity;

import java.util.List;

//Это не Util а DataClass
public class UserDTO {

    private String email;
    private String password;

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

    public UserDTO() {
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean checkAuth(UserDAO userDAO, UserDTO userDTO) {
        UserEntity user = userDAO.findByEmailAndPassword(userDTO);

        if (user == null) {
            return false;
        } else {
            return true;
        }

    }

}
