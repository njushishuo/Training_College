package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import training_college.repository.extra.PreScheduleInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by ss14 on 2017/3/17.
 */
@Transactional
public class PreScheduleRepositoryImpl implements PreScheduleInterface {

    @PersistenceContext
    EntityManager em;

    @Override
    public void deleteRelatedByPid(int pid) {
        String hql = "delete from PreModifySchedule pm  where pm.projectId = ?1";
        Query query = em.createQuery(hql).setParameter(1,pid);
        query.executeUpdate();
    }
}
