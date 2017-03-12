package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import training_college.entity.Project;
import training_college.repository.extra.ProjectInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ss14 on 2017/3/12.
 */
@Transactional
public class ProjectRepositoryImpl implements ProjectInterface {

    @PersistenceContext
    EntityManager em;

    @Override
    public List getOpenClassesByOrgId(int id) {
        String hql = "from Project where addStatus = 'approved' and organization.id = ?1 ";
        Query query   = em.createQuery(hql).setParameter(1,id);
        List<Project> projectList = query.getResultList();
        return projectList;
    }
}
