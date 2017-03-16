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
}
