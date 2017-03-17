package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import training_college.entity.PostModifySchedule;
import training_college.entity.PostModifySchedulePK;
import training_college.repository.extra.PostScheduleInterface;

import java.util.List;

/**
 * Created by ss14 on 2017/3/14.
 */
@Repository
public interface PostScheduleRepository
        extends JpaRepository<PostModifySchedule , PostModifySchedulePK>,PostScheduleInterface {

    List<PostModifySchedule> getByProjectId(int pid);

}
