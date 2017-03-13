package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.Course;
import training_college.entity.NewSchedule;
import training_college.entity.Project;
import training_college.repository.CourseRepository;
import training_college.repository.NewScheduleRepository;
import training_college.repository.ProjectRepository;
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

    @Override
    public Project saveAndFlush(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public List<Project> getAllNewClassesByOrgId(int id) {
        return projectRepository.getAllNewClassesByOrgId(id);
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
}
