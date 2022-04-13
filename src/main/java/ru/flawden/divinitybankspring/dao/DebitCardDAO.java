package ru.flawden.divinitybankspring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.flawden.divinitybankspring.dto.UserDTO;
import ru.flawden.divinitybankspring.entity.DebitCardEntity;
import ru.flawden.divinitybankspring.entity.UserEntity;

import javax.persistence.Query;
import java.util.List;

@Component
public class DebitCardDAO {

    private final SessionFactory sessionFactory;
    public static UserEntity authUser;

    @Autowired
    public DebitCardDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<DebitCardEntity> index(UserEntity authUser) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM DebitCardEntity WHERE user=:user");
        query.setParameter("user", authUser);
        List debitCardList = query.getResultList();
        return debitCardList;
    }

    @Transactional(readOnly = true)
    public DebitCardEntity show(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(DebitCardEntity.class, id);
    }

    @Transactional
    public void save(DebitCardEntity debitCard) {
        Session session = sessionFactory.getCurrentSession();
        session.save(debitCard);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(DebitCardEntity.class, id));
    }

}
