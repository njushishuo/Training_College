package training_college.repository.impl;


import org.springframework.transaction.annotation.Transactional;
import training_college.entity.Course;
import training_college.repository.extra.CourseInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ss14 on 2017/3/12.
 */
@Transactional
public class CourseRepositoryImpl implements CourseInterface {
    @PersistenceContext
    EntityManager em;

    @Override
    public List getPostModifyCoursesByProjectId(int id) {
        String hql = "select c from Course c  ,PostModifySchedule pm  where pm.projectId = ?1 and c.id = pm.courseId";
        Query query = em.createQuery(hql).setParameter(1,id);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public List getPreModifyCoursesByProjectId(int id) {

        String hql = "select c from Course c  ,PreModifySchedule pm  where pm.projectId = ?1 and c.id = pm.courseId";
        Query query = em.createQuery(hql).setParameter(1,id);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public List getNewCoursesByProjectId(int id) {

        String hql = "select c  from NewSchedule ns , Course c  where ns.projectId = ?1 and c.id = ns.courseId";
        Query query = em.createQuery(hql).setParameter(1,id);
        List<Course> courses = query.getResultList();
        return courses;

    }
}
