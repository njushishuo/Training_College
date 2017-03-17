package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training_college.entity.PreModifySchedule;
import training_college.entity.PreModifySchedulePK;
import training_college.repository.extra.PreScheduleInterface;

/**
 * Created by ss14 on 2017/3/12.
 */
public interface PreScheduleRepository extends
        JpaRepository<PreModifySchedule, PreModifySchedulePK>,PreScheduleInterface {
}
