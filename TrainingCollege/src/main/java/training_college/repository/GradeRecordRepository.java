package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.GradeRecord;
import training_college.repository.extra.GradeRecordInterface;

import java.util.List;

/**
 * Created by ss14 on 2017/3/14.
 */
@Repository
public interface GradeRecordRepository extends
        JpaRepository<GradeRecord,Integer>,GradeRecordInterface {

    List<GradeRecord> getByOrgSystemId (String id);


    List<GradeRecord> getByStudentName(String stdName);
}
