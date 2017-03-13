package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.NewSchedule;
import training_college.entity.NewSchedulePK;

/**
 * Created by ss14 on 2017/3/13.
 */
@Repository
public interface NewScheduleRepository extends JpaRepository<NewSchedule,NewSchedulePK> {
}
