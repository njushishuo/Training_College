package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.Course;
import training_college.entity.Project;
import training_college.repository.CourseRepository;
import training_college.repository.ProjectRepository;
import training_college.service.ClassInfoService;

import java.util.List;

/**
 * Created by ss14 on 2017/3/13.
 */
@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Project> getOpenClassesByOrgId(int id) {
        return projectRepository.getOpenProjectsByOrgId(id);
    }

    @Override
    public List<Project> getAllOpenProjects() {
        return projectRepository.getAllOpenProjects();
    }

    @Override
    public List<Course> getCoursesByProjectId(int id) {
        return courseRepository.getCoursesByProjectId(id);
    }
}
