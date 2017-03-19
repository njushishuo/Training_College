package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.*;
import training_college.repository.*;
import training_college.service.ApplyService;

import java.util.List;

/**
 * Created by ss14 on 2017/3/13.
 */
@Service
public class ApplyServiceImpl implements ApplyService {


    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    NewScheduleRepository newScheduleRepository;
    @Autowired
    PostScheduleRepository postScheduleRepository;
    @Autowired
    PostProjectRepository postProjectRepository;

    @Override
    public Project getProjectById(int pid) {
        return projectRepository.findOne(pid);
    }

    @Override
    public List<Project> getAllProjectsByOrgId(int id) {
        return projectRepository.getAllProjectsByOrgId(id);
    }

    @Override
    public List<Project> getAllModifiedProjectsByOrgId(int id) {

       return  projectRepository.getAllModifiedProjectsByOrgId(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void addNewSchedule(String[] courseIds, int projectId) {

        for (int i = 0; i < courseIds.length; i++) {
            NewSchedule newSchedule = new NewSchedule();
            newSchedule.setProjectId(projectId);
            newSchedule.setCourseId(Integer.parseInt(courseIds[i]));
            newScheduleRepository.save(newSchedule);
        }
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public void overWritePostProject(PostProject postProject) {

        //先删除上次的修改记录
        postProjectRepository.deleteRelatedByPid(postProject.getPid());
        //再添加新的记录
        postProjectRepository.saveAndFlush(postProject);

    }

    @Override
    public void overWritePostSchedule(String[] courseIds, int projectId) {

        //先删除上次的修改记录
        postScheduleRepository.deleteRelatedByPid(projectId);
        if(courseIds==null){
            return;
        }else if(courseIds.length==0){
            return;
        }
        //再添加新的记录
        for (int i = 0; i < courseIds.length; i++) {
            PostModifySchedule postModifySchedule = new PostModifySchedule();
            postModifySchedule.setProjectId(projectId);
            postModifySchedule.setCourseId(Integer.parseInt(courseIds[i]));
            postScheduleRepository.save(postModifySchedule);
        }
    }
}
