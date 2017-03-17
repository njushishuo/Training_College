package training_college.service;

import training_college.entity.NewSchedule;
import training_college.entity.PostModifySchedule;
import training_college.entity.PreModifySchedule;

import java.util.List;

/**
 * Created by ss14 on 2017/3/17.
 */
public interface ScheduleTransformService {

    List<PreModifySchedule> newToPreModify(List<NewSchedule> newSchedules);

    List<PreModifySchedule> postToPreModify(List<PostModifySchedule> postSchedules);

}
