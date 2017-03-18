package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import training_college.repository.extra.OrganizationInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by ss14 on 2017/3/18.
 */

@Transactional
public class OrganizationRepositoryImpl implements OrganizationInterface {


    @PersistenceContext
    EntityManager em;



    @Override
    public List<Integer> getAllOrgId() {

        String hql = "select o.id from Organization o ";
        Query query = em.createQuery(hql);
        List<Integer> ids = query.getResultList();
        return ids;
    }

    @Override
    public int getProjectCntByOrgId(int id) {
        String hql = "select count(p) from Project p where p.organization.id = ?1 ";
        Query query = em.createQuery(hql).setParameter(1,id);
        int cnt = (int)(long)query.getSingleResult();
        return cnt;
    }

    @Override
    public int getCourseCntByOrgId(int id) {
        String hql = "select count(pm.courseId) from Project p, PreModifySchedule pm " +
                     "where p.organization.id = ?1 and pm.projectId = p.id ";
        Query query = em.createQuery(hql).setParameter(1,id);
        int cnt = (int)(long)query.getSingleResult();
        return cnt;
    }

    @Override
    public int getCurStdCntByOrgId(int id) {
        String hql = "select count(p.curStdCnt) from Project p where p.organization.id = ?1 ";
        Query query = em.createQuery(hql).setParameter(1,id);
        int cnt = (int)(long)query.getSingleResult();
        return cnt;
    }
}
