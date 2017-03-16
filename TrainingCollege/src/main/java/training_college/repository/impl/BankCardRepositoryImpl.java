package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import training_college.entity.BankCard;
import training_college.repository.extra.BankCardInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

/**
 * Created by ss14 on 2017/3/16.
 */
@Transactional
public class BankCardRepositoryImpl implements BankCardInterface {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<BankCard> getByStudentId(int id) {
        String hql = "select bc from StudentBankCard  sbc , BankCard  bc where sbc.studentId = ?1 and sbc.bankCardId = bc.id ";
        Query query = em.createQuery(hql).setParameter(1,id);
        List<BankCard> bankCards = query.getResultList();
        return bankCards;
    }
}
