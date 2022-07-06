package ru.flawden.divinitybankspring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.LoanEntity;
import ru.flawden.divinitybankspring.entity.UserEntity;

@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;

    private final PasswordEncoder encoder;


    public UserService(UserDAO userDAO, PasswordEncoder encoder) {
        this.userDAO = userDAO;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByEmail(username);
    }

    public UserEntity findByEmail(String username) throws UsernameNotFoundException {
        return userDAO.findByEmail(username);
    }

    public void update(String email, UserEntity user) throws UsernameNotFoundException {
        boolean isLoginPasswordChanged = userDAO.update(email, user);
        if(isLoginPasswordChanged) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }

    public UserEntity save(UserEntity user) throws UsernameNotFoundException {
        user.setPassword(encoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    public void delete(String email) throws UsernameNotFoundException {
        userDAO.delete(email);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
