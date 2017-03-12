package training_college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import training_college.entity.Course;
import training_college.entity.Project;
import training_college.repository.CourseRepository;
import training_college.repository.ProjectRepository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ss14 on 2017/3/11.
 */
@Controller
public class OrganizationController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CourseRepository courseRepository;

    @RequestMapping(value = "/organization/{id}" , method = RequestMethod.GET)
    public String getClassInfoPage(@PathVariable  int id , Model model){

        List<Project> projectList = projectRepository.getOpenClassesByOrgId(id);
        HashMap courseMap = new HashMap();
        for (Project project : projectList){
            List<Course> courseList = courseRepository.getCoursesByProjectId(project.getId());
            courseMap.put(project.getId(),courseList);
        }

        model.addAttribute("projects",projectList);
        model.addAttribute("courseMap" , courseMap);

        return "class_info";
    }

}
