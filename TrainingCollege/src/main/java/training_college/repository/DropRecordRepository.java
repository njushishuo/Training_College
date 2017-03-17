package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.DropRecord;

import java.util.List;

/**
 * Created by ss14 on 2017/3/14.
 */
@Repository
public interface DropRecordRepository extends JpaRepository<DropRecord,Integer> {

    List<DropRecord> getByOrgSystemIdOrderByDateDesc(String id);


    List<DropRecord> getByStudentNameOrderByDateDesc(String id);

}
