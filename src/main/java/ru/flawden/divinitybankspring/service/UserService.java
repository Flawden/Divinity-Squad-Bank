package ru.flawden.divinitybankspring.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.flawden.divinitybankspring.dao.UserDAO;
import ru.flawden.divinitybankspring.entity.UserEntity;

@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByEmail(username);
    }

    public UserEntity findByEmail(String username) throws UsernameNotFoundException {
        return userDAO.findByEmail(username);
    }

    public void update(Long id, UserEntity user) throws UsernameNotFoundException {
        user.setId(0L);
        boolean isLoginPasswordChanged = userDAO.update(id, user);
        if(isLoginPasswordChanged) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }

    public UserEntity save(UserEntity user) throws UsernameNotFoundException {
        return userDAO.save(user);
    }

    public void delete(Long id) throws UsernameNotFoundException {
        userDAO.delete(id);
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
