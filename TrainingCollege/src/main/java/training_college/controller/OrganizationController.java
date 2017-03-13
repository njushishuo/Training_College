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
    @RequestMapping(value ="/organization/{id}/classInfo" , method = RequestMethod.GET)
    public String getClassInfoPage(@PathVariable  int id , Model model){

        List<Project> projectList = classInfoService.getOpenClassesByOrgId(id);
        HashMap courseMap = new HashMap();

        for (Project project : projectList){
            List<Course> courseList = classInfoService.getCoursesByProjectId(project.getId());
            courseMap.put(project.getId(),courseList);
        }

        model.addAttribute("projects",projectList);
        model.addAttribute("courseMap", courseMap);

        return "/organization/class_info";
    }

    /**
     * 获取申请开班界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/classCreation" , method = RequestMethod.GET)
    public String getNewClassPage(Model model){

        List<Course> courseList = applyService.getAllCourses();
        model.addAttribute("courses",courseList);

        return "/organization/class_creation";
    }

    @RequestMapping(value ="/organization/{id}/classCreation" , method = RequestMethod.POST)
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

        String courseIds[]=request.getParameterValues("checkbox_course");
        applyService.addNewSchedule(courseIds,project.getId());

        return "redirect:/organization/"+id+"/classCreationHistory";
    }


    /**
     * 获取申请开班历史记录界面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/classCreationHistory" , method = RequestMethod.GET)
    public String getNewClassHistoryPage(@PathVariable  int id , Model model){

        List<Project> projectList = applyService.getAllNewClassesByOrgId(id);
        HashMap courseMap = new HashMap();
        for (Project project : projectList){

            List<Course> courseList = classInfoService.getCoursesByProjectId(project.getId());
            courseMap.put(project.getId(),courseList);

        }

        model.addAttribute("projects",projectList);
        model.addAttribute("courseMap" , courseMap);

        return "/organization/class_creation_history";
    }



    /**
     * 获取申请修改班级信息界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/classModification/{pid}" , method = RequestMethod.GET)
    public String getClassModificationPage
        (@PathVariable int id, @PathVariable int pid,Model model){

        Project project = applyService.getClassById(pid);
        List<Course> courseList = applyService.getAllCourses();
        model.addAttribute("project",project );
        model.addAttribute("courses",courseList);

        return "/organization/class_modification";
    }


    /**
     * 获取申请修改班级信息界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/classModification/{pid}" , method = RequestMethod.POST)
    public String modifyClass(@PathVariable int id,@PathVariable int pid, HttpServletRequest request, HttpSession session ,Model model){

        String className = request.getParameter("className");
        int maxStdCnt = Integer.parseInt(request.getParameter("maxStdCnt"));
        Date fromDate = Date.valueOf(request.getParameter("fromDate"));
        Date toDate = Date.valueOf(request.getParameter("toDate"));
        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));

        /**
         * 获取原有的课程
         */
        Project project = applyService.getClassById(pid);
        project.setClassName(className);
        project.setMaxStdCnt(maxStdCnt);
        project.setFromDate(fromDate);
        project.setToDate(toDate);
        project.setTotalPrice(totalPrice);
        project.setAddStatus(AddStatus.pending);
        project.setOrganization((Organization) session.getAttribute("organization"));
        /**
         * 为了立即见效这里强制保存
         */
        project=applyService.saveAndFlush(project);

        /**
         * 将修改的情况添加到post_schedule中
         */
        String courseIds[]=request.getParameterValues("checkbox_course");
        applyService.addPostSchedule(courseIds,project.getId());

        return "redirect:/organization/"+id+"classModificationHistory";
    }

}
