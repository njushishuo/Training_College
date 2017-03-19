package training_college.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.QAbstractAuditable;
import org.springframework.transaction.annotation.Transactional;
import training_college.entity.Card;
import training_college.repository.extra.CardInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ss14 on 2017/3/19.
 */

@Transactional
public class CardRepositoryImpl implements CardInterface {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Card> getToFrozenCards() {

        String hql = " from Card c where c.balance < 1000 and c.status = 'activated'  ";
        Query query = em.createQuery(hql);

        return query.getResultList();
    }

    @Override
    public List<Card> getToDisableCards() {

        String hql = " from Card c where c.balance < 1000 and c.status = 'frozen'  ";
        Query query = em.createQuery(hql);

        return query.getResultList();
    }
}
