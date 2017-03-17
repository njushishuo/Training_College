package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import training_college.entity.Course;
import training_college.entity.EnrollmentRecord;
import training_college.repository.extra.EnrollRecordInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ss14 on 2017/3/16.
 */
@Transactional
public class EnrollRecordRepositoryImpl implements EnrollRecordInterface {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<EnrollmentRecord> getEnrollRecordsWithSelectionByStdName(String name) {
        String hql = "from EnrollmentRecord e where e.studentName = ?1 and e.selectMethod = 'select' ";
        Query query = em.createQuery(hql).setParameter(1,name);
        List<EnrollmentRecord> records = query.getResultList();
        return  records;
    }

    @Override
    public int getPaymentSumByOrgSystemId(String sysId) {
        String hql = "select sum(e.payment) " +
                "from EnrollmentRecord e " +
                "where e.payMethod='card' and e.orgSystemId =?1 ";
        Query query = em.createQuery(hql).setParameter(1,sysId);
        int sum = (int)(long) query.getSingleResult();
        return  sum;
    }

    @Override
    public int getRepaymentSumByOrgSystemId(String sysId) {

        String hql = "select sum(dr.payment) " +
                "from DropRecord dr " +
                "where dr.payMethod='card' and dr.orgSystemId =?1 ";
        Query query = em.createQuery(hql).setParameter(1,sysId);
        int sum = (int)(long) query.getSingleResult();

        return  sum;

    }

    @Override
    public List<String> getOrgSystemIds() {
        String hql = "select distinct e.orgSystemId from EnrollmentRecord e" ;
        Query query = em.createQuery(hql);
        return query.getResultList();
    }

}
