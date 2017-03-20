package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import training_college.repository.extra.DropRecordInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ss14 on 2017/3/18.
 */

@Transactional
public class DropRecordRepositoryImpl implements DropRecordInterface {
    @PersistenceContext
    EntityManager em;

    @Override
    public int getRepaymentSumByOrgSystemId(String sysId) {
        String hql = "select sum(d.payment) " +
                "from DropRecord d " +
                "where d.orgSystemId =?1 and d.payMethod='card' and d.checked = false";
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
    public boolean settleRepaymentByOrgSysId(String sysId) {
        String hql = "update DropRecord d " +
                "set d.checked = true " +
                "where d.payMethod='card' and d.orgSystemId =?1 ";
        Query query = em.createQuery(hql).setParameter(1,sysId);

        return  query.executeUpdate()==0 ? false:true ;
    }

    @Override
    public int getDropSumByOid(String oid) {
        String hql = "select sum(d.payment) from DropRecord  d where d.orgSystemId = ?1";
        Query query   = em.createQuery(hql).setParameter(1,oid);
        List result = query.getResultList();
        if(result!=null){
            if(result.get(0)!=null){
                return  (int)(long)result.get(0);
            }
        }
        return 0;
    }

    @Override
    public int getDropCntByOid(String oid) {
        String hql = "select count(d) from DropRecord  d where d.orgSystemId = ?1";
        Query query   = em.createQuery(hql).setParameter(1,oid);
        List result = query.getResultList();
        if(result!=null){
            if(result.get(0)!=null){
                return  (int)(long)result.get(0);
            }
        }
        return 0;
    }
}
