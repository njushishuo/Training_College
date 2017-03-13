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
    public List getCoursesByProjectId(int id) {

        String hql = "select c from Course c  where c.id  in  " +
                "(select c1.id  from PreModifySchedule pm ,Course c1  where pm.projectId = ?1 and c1.id = pm.courseId)";
        Query query = em.createQuery(hql).setParameter(1,id);
        List<Course> courses = query.getResultList();
        return courses;
    }
}
