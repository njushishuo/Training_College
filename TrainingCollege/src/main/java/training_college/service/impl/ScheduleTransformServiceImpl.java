package training_college.service.impl;

import org.springframework.stereotype.Service;
import training_college.entity.NewSchedule;
import training_college.entity.PreModifySchedule;
import training_college.service.ScheduleTransformService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ss14 on 2017/3/17.
 */

@Service
public class ScheduleTransformServiceImpl implements ScheduleTransformService {



    @Override
    public List<PreModifySchedule> newToPreModify(List<NewSchedule> newSchedules) {

        List<PreModifySchedule> preModifySchedules = new LinkedList<>();

        for(NewSchedule newSchedule : newSchedules){
            PreModifySchedule preModifySchedule = new PreModifySchedule();
            preModifySchedule.setCourseId(newSchedule.getCourseId() );
            preModifySchedule.setProjectId(newSchedule.getProjectId());

            preModifySchedules.add(preModifySchedule);
        }

        return preModifySchedules;
    }
}
