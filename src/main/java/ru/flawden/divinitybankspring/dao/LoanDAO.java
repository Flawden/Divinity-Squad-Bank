package ru.flawden.divinitybankspring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.flawden.divinitybankspring.entity.DebitCardEntity;
import ru.flawden.divinitybankspring.entity.LoanEntity;
import ru.flawden.divinitybankspring.entity.UserEntity;

import javax.persistence.Query;
import java.util.List;

@Component
public class LoanDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public LoanDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<LoanEntity> index(UserEntity authUser) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM LoanEntity WHERE loanOwner=:user");
        query.setParameter("user", authUser);
        List loanList = query.getResultList();
        return loanList;
    }



}
