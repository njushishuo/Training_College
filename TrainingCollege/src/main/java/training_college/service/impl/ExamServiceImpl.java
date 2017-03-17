package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training_college.entity.*;
import training_college.repository.*;
import training_college.service.ExamService;
import training_college.service.ScheduleTransformService;
import training_college.util.enumeration.AddStatus;
import training_college.util.enumeration.ModifyStatus;

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
    PostProjectRepository postProjectRepository;
    @Autowired
    NewScheduleRepository newScheduleRepository;
    @Autowired
    PreScheduleRepository preScheduleRepository;
    @Autowired
    PostScheduleRepository postScheduleRepository;


    @Override
    public List<Project> getPendingNewProjects() {
        return projectRepository.getPendingNewProjects();
    }

    @Override
    public List<Project> getAllProcessedNewProjects() {
        return projectRepository.getAllProcessedNewProjects();
    }

    @Override
    public List<Project> getPendingModifyProjects() {
        return projectRepository.getPendingModifyProjects();
    }

    @Override
    public List<Project> getAllProcessedModifyProjects() {
        return projectRepository.getAllProcessedModifyProjects();
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

    @Override
    public boolean approveModifyProject(int pid) {

        //S1:project的modify_status改为approved
        Project project = projectRepository.findOne(pid);
        project.setModifyStatus(ModifyStatus.approved);

        //s2:用postProject覆盖project的信息
        PostProject postProject = postProjectRepository.getOne(pid);
        project.setClassName(postProject.getClassName());
        project.setFromDate(postProject.getFromDate());
        project.setToDate(postProject.getToDate());
        project.setTotalPrice(postProject.getTotalPrice());
        projectRepository.saveAndFlush(project);


        //S3:删除pre_modify_schedule中原有记录
        preScheduleRepository.deleteRelatedByPid(pid);
        //S4:post_modify_schedule中该pid对应的记录复制到pre_modify_schedule
        List<PostModifySchedule> postModifySchedules = postScheduleRepository.getByProjectId(pid);
        List<PreModifySchedule> preModifySchedules = transformService.postToPreModify(postModifySchedules);
        preScheduleRepository.save(preModifySchedules);

        return true;
    }

    @Override
    public boolean rejectModifyProject(int pid) {

        //S1:project的modify_status改为rejected
        Project project = projectRepository.findOne(pid);
        project.setModifyStatus(ModifyStatus.rejected);
        projectRepository.saveAndFlush(project);

        return  true;
    }
}
