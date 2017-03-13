package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training_college.entity.PostModifySchedule;
import training_college.entity.PostModifySchedulePK;

/**
 * Created by ss14 on 2017/3/14.
 */
public interface PostScheduleRepository extends JpaRepository<PostModifySchedule , PostModifySchedulePK> {
}
