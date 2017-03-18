package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import training_college.repository.extra.GradeRecordInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ss14 on 2017/3/18.
 */

@Transactional
public class GradeRecordRepositoryImpl implements GradeRecordInterface {


    @PersistenceContext
    EntityManager em;
    @Override
    public List<Object []> getCourseStatsByOrgSysId(String oid) {

        String hql = "select g.courseName,min(g.score),max(g.score),avg(g.score) " +
                     "from GradeRecord g " +
                     "where g.orgSystemId = ?1 " +
                     "group by g.courseName";

        Query query = em.createQuery(hql).setParameter(1,oid);
        List result = query.getResultList();
        return result;
    }

}
