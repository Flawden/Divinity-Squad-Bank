package ru.flawden.divinitybankspring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.flawden.divinitybankspring.entity.DebitCardEntity;
import ru.flawden.divinitybankspring.entity.UserEntity;

import javax.persistence.Query;
import java.util.List;

@Component
public class DebitCardDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public DebitCardDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<DebitCardEntity> index(UserEntity authUser) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM DebitCardEntity WHERE cardOwner=:user");
        query.setParameter("user", authUser);
        List debitCardList = query.getResultList();
        return debitCardList;
    }

    @Transactional(readOnly = true)
    public Double getBalance(UserEntity authUser) {
        Session session = sessionFactory.getCurrentSession();

        Double sum = 0.0;

        Query query = session.createQuery("FROM DebitCardEntity WHERE cardOwner=:user");
        query.setParameter("user", authUser);
        List<DebitCardEntity> debitCardList = query.getResultList();

        for (DebitCardEntity debitCard: debitCardList) {
            sum += debitCard.getBalance();
        }

        return sum;
    }

    @Transactional(readOnly = true)
    public List<DebitCardEntity> getTwoDebitCard(UserEntity authUser) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM DebitCardEntity WHERE cardOwner=:user");
        query.setParameter("user", authUser);
        query.setMaxResults(2);
        List debitCardList = query.getResultList();
        return debitCardList;
    }

    @Transactional(readOnly = true)
    public DebitCardEntity show(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(DebitCardEntity.class, id);
    }

}
