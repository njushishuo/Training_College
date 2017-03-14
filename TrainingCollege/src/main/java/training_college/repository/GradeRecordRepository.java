package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.GradeRecord;

import java.util.List;

/**
 * Created by ss14 on 2017/3/14.
 */
@Repository
public interface GradeRecordRepository extends JpaRepository<GradeRecord,Integer> {

    List<GradeRecord> getByOrgSystemId (String id);
}
