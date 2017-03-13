package training_college.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import training_college.entity.*;
import training_college.repository.CourseRepository;
import training_college.repository.NewScheduleRepository;
import training_college.repository.ProjectRepository;
import training_college.service.ApplyService;
import training_college.service.ClassInfoService;
import training_college.util.enumeration.AddStatus;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ss14 on 2017/3/11.
 */
@Controller
public class OrganizationController {

    @Autowired
    ClassInfoService classInfoService;
    @Autowired
    ApplyService applyService;


    /**
     * 获取班级信息界面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/organization/{id}/classInfo" , method = RequestMethod.GET)
    public String getClassInfoPage(@PathVariable  int id , Model model){

        List<Project> projectList = classInfoService.getOpenClassesByOrgId(id);
        HashMap courseMap = new HashMap();

        for (Project project : projectList){
            List<Course> courseList = classInfoService.getCoursesByProjectId(project.getId());
            courseMap.put(project.getId(),courseList);
        }

        model.addAttribute("projects",projectList);
        model.addAttribute("courseMap", courseMap);

        return "class_info";
    }

    /**
     * 获取申请开班界面
     * @param model
     * @return
     */
    @RequestMapping(value = "/organization/{id}/classCreation" , method = RequestMethod.GET)
    public String getNewClassPage(Model model){

        List<Course> courseList = applyService.getAllCourses();
        model.addAttribute("courses",courseList);

        return "class_creation";
    }

    @RequestMapping(value = "/organization/{id}/classCreation" , method = RequestMethod.POST)
    public String newClass(@PathVariable  int id , HttpServletRequest request, HttpSession session, Model model){

        String className = request.getParameter("className");
        int maxStdCnt = Integer.parseInt(request.getParameter("maxStdCnt"));
        Date fromDate = Date.valueOf(request.getParameter("fromDate"));
        Date toDate = Date.valueOf(request.getParameter("toDate"));
        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));

        Project project = new Project();
        project.setClassName(className);
        project.setMaxStdCnt(maxStdCnt);
        project.setFromDate(fromDate);
        project.setToDate(toDate);
        project.setTotalPrice(totalPrice);
        project.setAddStatus(AddStatus.pending);
        project.setOrganization((Organization) session.getAttribute("organization"));

        project=applyService.saveAndFlush(project);

//        if(project.getId()!=0){
//            System.err.println(project.getId());
//        }else{
//            System.err.println("project id is 0");
//        }

        String courseIds[]=request.getParameterValues("checkbox_course");

        applyService.addNewSchedule(courseIds,project.getId());

        return "redirect:/organization/"+id+"/newClassHistory";
    }


    /**
     * 获取申请开班历史记录界面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/organization/{id}/classCreationHistory" , method = RequestMethod.GET)
    public String getNewClassHistoryPage(@PathVariable  int id , Model model){

        List<Project> projectList = applyService.getAllNewClassesByOrgId(id);
        HashMap courseMap = new HashMap();
        for (Project project : projectList){

            List<Course> courseList = classInfoService.getCoursesByProjectId(project.getId());
            courseMap.put(project.getId(),courseList);

        }

        model.addAttribute("projects",projectList);
        model.addAttribute("courseMap" , courseMap);

        return "class_creation_history";
    }



    /**
     * 获取申请修改班级信息界面
     * @param model
     * @return
     */
    @RequestMapping(value = "/organization/{id}/classModification" , method = RequestMethod.GET)
    public String getClassModificationPage
        (HttpSession session ,Model model){

        List<Course> courseList = applyService.getAllCourses();
        model.addAttribute("courses",courseList);

        return "class_modification";
    }



}
