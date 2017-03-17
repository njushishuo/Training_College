package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import training_college.repository.extra.PostScheduleInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by ss14 on 2017/3/17.
 */
@Transactional
public class PostScheduleRepositoryImpl implements PostScheduleInterface {
    @PersistenceContext
    EntityManager em;


    @Override
    public void deleteRelatedByPid(int pid) {
        String hql = "delete from PostModifySchedule pm  where pm.projectId = ?1";
        Query query = em.createQuery(hql).setParameter(1,pid);
        query.executeUpdate();
    }
}
