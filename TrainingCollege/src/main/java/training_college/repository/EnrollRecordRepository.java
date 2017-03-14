package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.EnrollmentRecord;

import java.util.List;


/**
 * Created by ss14 on 2017/3/14.
 */
@Repository
public interface EnrollRecordRepository extends JpaRepository<EnrollmentRecord,Integer> {

    List<EnrollmentRecord> getByOrgSystemId (String id);

}
