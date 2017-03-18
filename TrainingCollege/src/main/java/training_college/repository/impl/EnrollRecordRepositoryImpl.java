package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
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
        List result = query.getResultList();
        if(result!=null){
            if(result.get(0)!=null){
                return (int)(long)result.get(0);
            }
        }
        return  0;
    }

    @Override
    public boolean settlePaymentByOrgSysId(String sysId) {
        String hql = "update EnrollmentRecord e " +
                "set e.checked = true " +
                "where e.payMethod='card' and e.orgSystemId =?1 ";
        Query query = em.createQuery(hql).setParameter(1,sysId);

        return  query.executeUpdate()==0 ? false:true ;
    }

    @Override
    public List<String> getPaymentUncheckedOrgSystemIds() {

        String hql = "select distinct e.orgSystemId " +
                "from EnrollmentRecord e where e.payMethod = 'card' and e.checked is false " ;

        Query query = em.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public int getEnrollSumByOid(String oid) {

        String hql = "select sum(e.payment) " +
                "from EnrollmentRecord e where e.orgSystemId = ?1 " ;
        Query query = em.createQuery(hql).setParameter(1,oid);
        int sum = (int )(long )query.getSingleResult();
        return sum;
    }

}
