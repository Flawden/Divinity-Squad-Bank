package ru.flawden.divinitybankspring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.flawden.divinitybankspring.dto.UserDTO;
import ru.flawden.divinitybankspring.dto.UserDetailsDTO;
import ru.flawden.divinitybankspring.entity.DebitCardEntity;
import ru.flawden.divinitybankspring.entity.LoanEntity;
import ru.flawden.divinitybankspring.entity.UserEntity;

import javax.persistence.Query;
import java.util.List;

@Component
public class UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
        } catch (Exception e) {

        }
        return userEntity;
    }

    @Transactional(readOnly = true)
    public UserEntity findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserEntity where email=:email");
        query.setParameter("email", email);
        List userList = query.getResultList();
        UserEntity userEntity = null;
        try {
            userEntity = (UserEntity) userList.get(0);
        } catch (Exception e) {

        }
        return userEntity;
    }

    @Transactional
    public UserEntity save(UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user = findByEmail(user.getEmail());
    }

    @Transactional
    public UserDetailsDTO findUserDetailByUsername(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT id, firstName, lastName, email FROM UserEntity WHERE email=:email");
        query.setParameter("email", email);
        List userList = query.getResultList();
        UserDetailsDTO user = null;
        try {
            user = (UserDetailsDTO) userList.get(0);
        } catch (Exception e) {

        }
        return user;
    }

    @Transactional
    public Boolean checkCardNumExist(String num) {
        Session session = sessionFactory.getCurrentSession();
        boolean exist = false;
        Query query = session.createQuery("FROM DebitCardEntity WHERE cardNumber=:cardNumber");
        query.setParameter("cardNumber", num);
        List<String> cardNums = query.getResultList();
        if (!cardNums.isEmpty()) {
            return exist = true;
        }

        System.out.println(num);
        return exist;
    }
    //К переезду
    @Transactional
    public void addDebitCard(DebitCardEntity debitCard, UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        debitCard.setUser(user);
        session.save(debitCard);
    }
//К переезду
    @Transactional
    public void addLoan(LoanEntity loan, UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        loan.setUser(user);
        System.out.println(loan);
        session.save(loan);
    }

    @Transactional
    public boolean update(String email, UserEntity currentUser) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity personToBeUpdated = session.get(UserEntity.class, email);

        boolean isLoginPasswordChanged = false;

        if ((!(personToBeUpdated.getEmail().equals(currentUser.getEmail())) || (!(personToBeUpdated.getPassword().equals(currentUser.getPassword()))))) {
            isLoginPasswordChanged = true;
        }

        personToBeUpdated.setFirstName(currentUser.getFirstName());
        personToBeUpdated.setLastName(currentUser.getLastName());
        personToBeUpdated.setEmail(currentUser.getEmail());
        personToBeUpdated.setPassword(currentUser.getPassword());

        return isLoginPasswordChanged;
    }

    @Transactional
    public void delete(String email) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(UserEntity.class, email));
    }

}
