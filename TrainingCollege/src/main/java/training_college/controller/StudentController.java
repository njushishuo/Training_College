package training_college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import training_college.entity.Course;
import training_college.entity.Project;
import training_college.service.ClassInfoService;
import training_college.util.DateHelper;
import training_college.vo.ProjectVO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ss14 on 2017/3/15.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    ClassInfoService classInfoService;
    @Autowired
    DateHelper dateHelper;

    @RequestMapping(value = "/classInfo" , method = RequestMethod.GET)
    public String getClassInfoPage(Model model){

        List<Project> projects = classInfoService.getAllOpenProjects();
        HashMap courseMap = new HashMap();
        List<ProjectVO> projectVOs = new LinkedList<>();

        for (Project project : projects){
            List<Course> courseList = classInfoService.getCoursesByProjectId(project.getId());
            courseMap.put(project.getId(),courseList);

            ProjectVO projectVO = new ProjectVO();
            projectVO.project = project;
            projectVO.hasStarted =dateHelper.HasStarted(project.getFromDate());
            projectVOs.add(projectVO);
        }


        model.addAttribute("projectVOs",projectVOs);
        model.addAttribute("courseMap", courseMap);

        return "/student/class_info";

    }


}
