package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training_college.entity.NewSchedule;
import training_college.entity.PreModifySchedule;
import training_college.entity.Project;
import training_college.repository.NewScheduleRepository;
import training_college.repository.PreScheduleRepository;
import training_college.repository.ProjectRepository;
import training_college.service.ExamService;
import training_college.service.ScheduleTransformService;
import training_college.util.enumeration.AddStatus;

import java.util.List;

/**
 * Created by ss14 on 2017/3/17.
 */
@Service
public class ExamServiceImpl implements ExamService {


    @Autowired
    ScheduleTransformService transformService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    NewScheduleRepository newScheduleRepository;
    @Autowired
    PreScheduleRepository preScheduleRepository;


    @Override
    public List<Project> getPendingNewProjects() {
        return projectRepository.getPendingNewProjects();
    }

    @Override
    public List<Project> getAllProcessedNewProjects() {
        return projectRepository.getAllProcessedNewProjects();
    }

    @Override
    @Transactional
    public boolean approveNewProject(int pid) {


        //S1:project的add_status改为approved
        Project project = projectRepository.findOne(pid);
        project.setAddStatus(AddStatus.approved);
        projectRepository.saveAndFlush(project);


        //将new_schedule中该pid对应的记录，复制到pre_modify_schedule中
        List<NewSchedule> newSchedules = newScheduleRepository.getByProjectId(pid);
        List<PreModifySchedule> preModifySchedules = transformService.newToPreModify(newSchedules);
        preScheduleRepository.save(preModifySchedules);

        return true;
    }

    @Override
    public boolean rejectNewProject(int pid) {
        //S1:project的add_status改为rejected
        Project project = projectRepository.findOne(pid);
        project.setAddStatus(AddStatus.rejected);
        projectRepository.saveAndFlush(project);

        return true;
    }
}
