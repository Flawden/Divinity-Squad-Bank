package ru.flawden.divinitybankspring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.flawden.divinitybankspring.dto.UserDTO;
import ru.flawden.divinitybankspring.entity.UserEntity;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private final SessionFactory sessionFactory;
    public static UserEntity authUser;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<UserEntity> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from UserEntity p", UserEntity.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public UserEntity show(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserEntity.class, id);
    }

    @Transactional(readOnly = true)
    public UserEntity findByEmailAndPassword(UserDTO userDTO) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserEntity where email=:email and password=:password");
        query.setParameter("email", userDTO.getEmail());
        query.setParameter("password", userDTO.getPassword());
        List userList = query.getResultList();
        UserEntity userEntity = null;
        try {
            userEntity = (UserEntity) userList.get(0);
            authUser = userEntity;
        } catch (Exception e) {

        }
        return userEntity;
    }

    @Transactional
    public void save(UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public void update(int id, UserEntity updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity personToBeUpdated = session.get(UserEntity.class, id);

        personToBeUpdated.setFirstName(updatedUser.getFirstName());
        personToBeUpdated.setLastName(updatedUser.getLastName());
        personToBeUpdated.setEmail(updatedUser.getEmail());
        personToBeUpdated.setPassword(updatedUser.getPassword());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(UserEntity.class, id));
    }
}
