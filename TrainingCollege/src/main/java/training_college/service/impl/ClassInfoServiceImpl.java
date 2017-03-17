package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.Course;
import training_college.entity.Project;
import training_college.repository.CourseRepository;
import training_college.repository.ProjectRepository;
import training_college.service.ClassInfoService;
import training_college.util.DateHelper;
import training_college.vo.ClassInfoVO;
import training_college.vo.ProjectVO;

import java.util.HashMap;
import java.util.LinkedList;
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
    @Autowired
    DateHelper dateHelper;

    @Override
    public List<Project> getOpenClassesByOrgId(int id) {
        return projectRepository.getOpenProjectsByOrgId(id);
    }

    @Override
    public List<Project> getAllOpenProjects() {
        return projectRepository.getAllOpenProjects();
    }

    @Override
    public List<Project> getAvaliableProjectsNotSelectedByStdId(int id) {
        return projectRepository.getAvaliableProjectsNotSelectedByStdId(id);
    }

    @Override
    public List<Project> getStartedProjectsByStdId(int id) {
        return projectRepository.getStartedProjectsByStdId(id);
    }

    @Override
    public HashMap getPreModifyCourseMapByProejcts(List<Project> projects) {
        HashMap courseMap = new HashMap();

        for (Project project : projects){
            List<Course> courseList = this.getPreModifyCoursesByProjectId(project.getId());
            courseMap.put(project.getId(),courseList);
        }
        return courseMap;
    }

    @Override
    public HashMap getNewCourseMapByProejcts(List<Project> projects) {
        HashMap courseMap = new HashMap();

        for (Project project : projects){
            List<Course> courseList = this.getNewCoursesByProjectId(project.getId());
            courseMap.put(project.getId(),courseList);
        }
        return courseMap;
    }

    @Override
    public ClassInfoVO getClassInfoVOByProjects(List<Project> projects) {

        HashMap courseMap = new HashMap();
        List<ProjectVO> projectVOs = new LinkedList<>();

        for (Project project : projects){
            List<Course> courseList = this.getPreModifyCoursesByProjectId(project.getId());
            courseMap.put(project.getId(),courseList);

            ProjectVO projectVO = new ProjectVO();
            projectVO.project = project;
            projectVO.hasStarted =dateHelper.HasStarted(project.getFromDate());
            projectVOs.add(projectVO);
        }

        ClassInfoVO classInfoVO = new ClassInfoVO();
        classInfoVO.projectVOList = projectVOs;
        classInfoVO.courseMap = courseMap;
        return classInfoVO;

    }


    @Override
    public List<Course> getPreModifyCoursesByProjectId(int id) {
        return courseRepository.getPreModifyCoursesByProjectId(id);
    }

    @Override
    public List<Course> getNewCoursesByProjectId(int id) {
        return courseRepository.getNewCoursesByProjectId(id);
    }
}
