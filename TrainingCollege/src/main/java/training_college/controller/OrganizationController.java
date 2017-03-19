package training_college.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import training_college.entity.*;
import training_college.service.*;
import training_college.util.enumeration.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ss14 on 2017/3/11.
 */
@Controller
public class OrganizationController {

    @Autowired
    ClassInfoService classInfoService;
    @Autowired
    ApplyService applyService;
    @Autowired
    RecordService recordService;
    @Autowired
    SelectService selectService;
    @Autowired
    StatsService statsService;

/*****************************************班级信息*******************************************************/

    /**
     * 获取班级信息界面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/classInfo" , method = RequestMethod.GET)
    public String getClassInfoPage(@PathVariable  int id , Model model){

        List<Project> projectList = classInfoService.getOpenClassesByOrgId(id);
        Map courseMap = classInfoService.getPreModifyCourseMapByProejcts(projectList);

        model.addAttribute("projects",projectList);
        model.addAttribute("courseMap", courseMap);

        return "/organization/class_info";
    }


/*****************************************申请*******************************************************/

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

        project=applyService.addProject(project);

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

        List<Project> projectList = applyService.getAllProjectsByOrgId(id);
        HashMap courseMap = new HashMap();
        for (Project project : projectList){

            List<Course> courseList = classInfoService.getPreModifyCoursesByProjectId(project.getId());
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

        Project project = applyService.getProjectById(pid);
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
        Date fromDate = Date.valueOf(request.getParameter("fromDate"));
        Date toDate = Date.valueOf(request.getParameter("toDate"));
        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));


        /**
         * 修改project modifystatus状态
         */
        Project project = applyService.getProjectById(pid);
        project.setModifyStatus(ModifyStatus.pending);


        /**
         * 创建postproject
         */
        PostProject postProject = new PostProject();
        postProject.setPid(pid);
        postProject.setClassName(className);
        postProject.setFromDate(fromDate);
        postProject.setToDate(toDate);
        postProject.setTotalPrice(totalPrice);

        applyService.overWritePostProject(postProject);

        /**
         * 将修改的情况添加到post_schedule中
         */
        String courseIds[]=request.getParameterValues("checkbox_course");
        applyService.overWritePostSchedule(courseIds,pid);

        return "redirect:/organization/"+id+"/classModificationHistory";
    }


    /**
     * 获取申请修改班级历史界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/classModificationHistory" , method = RequestMethod.GET)
    public String getClassModificationHistoryPage(@PathVariable int id,Model model){

        List<Project> modifiedProjectList = applyService.getAllModifiedProjectsByOrgId(id);
        //TODO html null check
        if(modifiedProjectList==null){
            return "/organization/class_modification_history";
        }else if(modifiedProjectList.size()==0){
            return "/organization/class_modification_history";
        }
        HashMap courseMap = new HashMap();

        for (Project modifiedProject : modifiedProjectList){
            List<Course> courseList = classInfoService.getPreModifyCoursesByProjectId(modifiedProject.getId());
            courseMap.put(modifiedProject.getId(),courseList);
        }

        model.addAttribute("projects", modifiedProjectList);
        model.addAttribute("courseMap" , courseMap);

        return "/organization/class_modification_history";

    }


/*****************************************登记*******************************************************/

    /**
     * 获取添加入学记录界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/enrollRecordCreation" , method = RequestMethod.GET)
    public String getEnrollRecordCreationPage(@PathVariable int id,Model model){

        //获取那些开放且人数未满的班级
        List<Project> projects = recordService.getAvaliableProjectsByOrgId(id);
        model.addAttribute("projects",projects);

        return "/organization/enroll_record_creation";

    }


    /**
     * 添加入学记录
     * @return
     */
    @RequestMapping(value ="/organization/{id}/enrollRecordCreation" , method = RequestMethod.POST)
    public String addEnrollRecord(@PathVariable int id,HttpServletRequest request,HttpSession session){


        int  pid = Integer.parseInt(request.getParameter("classId"));
        String className = recordService.getProjectNameByPid(pid);

        String studentName = request.getParameter("studentName");
        String userTypeString = request.getParameter("userType");
        String payMethodString = request.getParameter("payMethod");
        int price = Integer.parseInt(request.getParameter("price"));
        int payment = Integer.parseInt(request.getParameter("payment"));


        EnrollmentRecord enrollmentRecord = new EnrollmentRecord();
        enrollmentRecord.setOrgSystemId(recordService.validateId(id));
        enrollmentRecord.setProjectName(className);
        enrollmentRecord.setStudentName(studentName);
        enrollmentRecord.setUserType(UserType.valueOf(userTypeString));
        enrollmentRecord.setPayMethod(PayMethod.valueOf(payMethodString));
        enrollmentRecord.setSelectMethod(SelectMethod.select);
        enrollmentRecord.setPrice(price);
        enrollmentRecord.setPayment(payment);

        Organization organization = (Organization) session.getAttribute("organization");

        //会员线下选课
        if(userTypeString.equals("member")){
            int sid  = selectService.getSidBySname(studentName);
            selectService.selectOffline(sid,pid,payment);
        }else{
            recordService.nonMemberEnroll(enrollmentRecord,organization);
        }

        return "redirect:/organization/"+id+"/enrollRecordCreation";

    }


    /**
     * 获取入学记录信息界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/enrollRecordInfo" , method = RequestMethod.GET)
    public String getEnrollRecordInfoPage(@PathVariable int id,Model model){

        List<EnrollmentRecord> records = recordService.getAllEnrollRecordsByOrgId(id);
        model.addAttribute("enrollRecords",records);
        return "/organization/enroll_record_info";
    }


    /**
     * 获取添加退课记录界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/dropRecordCreation" , method = RequestMethod.GET)
    public String getDropRecordCreationPage(@PathVariable int id,Model model){

        //获取开放的已选人数>0的班级
        List<Project> projects = recordService.getSelectedProjectsByOrgId(id);
        model.addAttribute("projects",projects);
        return "/organization/drop_record_creation";

    }


    /**
     * 添加退课记录
     * @return
     */
    @RequestMapping(value ="/organization/{id}/dropRecordCreation" , method = RequestMethod.POST)
    public String addDropRecord(@PathVariable int id,HttpServletRequest request ,HttpSession session){


        int  pid = Integer.parseInt(request.getParameter("classId"));
        String className = recordService.getProjectNameByPid(pid);
        String studentName = request.getParameter("studentName");
        String userTypeString = request.getParameter("userType");
        String payMethodString = request.getParameter("payMethod");
        int price = Integer.parseInt(request.getParameter("price"));
        int payment = Integer.parseInt(request.getParameter("payment"));


        DropRecord dropRecord = new DropRecord();
        dropRecord.setOrgSystemId(recordService.validateId(id));
        dropRecord.setProjectName(className);
        dropRecord.setStudentName(studentName);
        dropRecord.setUserType(UserType.valueOf(userTypeString));
        dropRecord.setPayMethod(PayMethod.valueOf(payMethodString));
        dropRecord.setSelectMethod(SelectMethod.select);
        dropRecord.setPrice(price);
        dropRecord.setPayment(payment);

        Organization organization = (Organization) session.getAttribute("organization");

        //会员线下退课
        if(userTypeString.equals("member")){
            int sid  = selectService.getSidBySname(studentName);
            selectService.unselectOffline(sid,pid,payment);
        }else{
            recordService.nonMemberDrop(dropRecord,organization);
        }


        return "redirect:/organization/"+id+"/dropRecordCreation";

    }


    /**
     * 获取退课记录信息界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/dropRecordInfo" , method = RequestMethod.GET)
    public String getDropRecordInfoPage(@PathVariable int id,Model model){

        List<DropRecord> records = recordService.getAllDropRecordsByOrgId(id);
        model.addAttribute("dropRecords",records);

        return "/organization/drop_record_info";

    }



    @ResponseBody
    @RequestMapping(value ="/organization/classPrice/{pid}" , method = RequestMethod.GET)
    public int getClassPrice(@PathVariable int pid){

        return classInfoService.getPriceByPid(pid);
    }


    /**
     * 获取添加成绩记录界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/gradeRecordCreation" , method = RequestMethod.GET)
    public String getGradeRecordCreationPage(@PathVariable int id,Model model){
        List<Project> projectList = recordService.getSelectedProjectsByOrgId(id);
        List<Course> courseList = applyService.getAllCourses();
        model.addAttribute("projects",projectList);
        model.addAttribute("courses",courseList);
        return "/organization/grade_record_creation";
    }


    /**
     * 添加成绩记录
     * @return
     */
    @RequestMapping(value ="/organization/{id}/gradeRecordCreation" , method = RequestMethod.POST)
    public String addGradeRecord(@PathVariable int id,HttpServletRequest request){


        String className = request.getParameter("className");
        String courseName = request.getParameter("courseName");
        String studentName = request.getParameter("studentName");
        int score = Integer.parseInt(request.getParameter("score"));

        GradeRecord gradeRecord = new GradeRecord();
        gradeRecord.setOrgSystemId(recordService.validateId(id));
        gradeRecord.setProjectName(className);
        gradeRecord.setCourseName(courseName);
        gradeRecord.setStudentName(studentName);
        gradeRecord.setScore(score);

        recordService.addGradeRecord(gradeRecord);

        return "redirect:/organization/"+id+"/gradeRecordCreation";

    }

    /**
     * 获取成绩记录信息界面
     * @param model
     * @return
     */
    @RequestMapping(value ="/organization/{id}/gradeRecordInfo" , method = RequestMethod.GET)
    public String getGradeRecordInfoPage(@PathVariable int id,Model model){

        List<GradeRecord> records = recordService.getAllGradeRecordsByOrgId(id);
        model.addAttribute("gradeRecords", records);

        return "/organization/grade_record_info";

    }


/*****************************************机构信息*******************************************************/

    @RequestMapping(value = "/organization/{id}/statistics/recruit" , method = RequestMethod.GET)
    public String getStatsRecruitPage(@PathVariable int id, Model model){


        //获取所有预订记录
        List<Reservation> reservations = statsService.getReservationByOid(id);
        //获取所有退订记录
        List<Reservation> unReservations = statsService.getUnreservationByOid(id);

        //获取所有选课记录
        List<EnrollmentRecord> enrollmentRecords = statsService.getEnrollRecordByOid(id);
        //获取所有退课记录
        List<DropRecord> dropRecords = statsService.getDropRecordByOid(id);


        model.addAttribute("reservations",reservations);
        model.addAttribute("unReservations",unReservations);
        model.addAttribute("enrollments",enrollmentRecords);
        model.addAttribute("dropRecords",dropRecords);

        return "/organization/stats_recruit";

    }

    @RequestMapping(value = "/organization/{id}/statistics/study" , method = RequestMethod.GET)
    public String getStasStudyPage(@PathVariable int id, Model model){

        //获取该机构每门课的选课人数，最高分，最低分，平均分
        List<Object[]> courseStatsList = statsService.getCourseStatsByOid(id);
        model.addAttribute("courseStatsList",courseStatsList);

        return "/organization/stats_study";
    }

    @RequestMapping(value = "/organization/{id}/statistics/finance" , method = RequestMethod.GET)
    public String getStasFinancePage(@PathVariable int id , Model model){


        //获取所有预订记录
        List<Reservation> reservations = statsService.getReservationByOid(id);
        int reserveSum = statsService.getReserveSumByOid(id);

        //获取所有退订记录
        List<Reservation> unReservations = statsService.getUnreservationByOid(id);
        int unReserveSum = statsService.getUnreserveSumByOid(id);

        //获取所有选课记录
        List<EnrollmentRecord> enrollmentRecords = statsService.getEnrollRecordByOid(id);
        int enrollSum = statsService.getEnrollSumByOid(id);

        //获取所有退课记录
        List<DropRecord> dropRecords = statsService.getDropRecordByOid(id);
        int dropSum = statsService.getDropSumByOid(id);

        model.addAttribute("reservations",reservations);
        model.addAttribute("unReservations",unReservations);
        model.addAttribute("enrollments",enrollmentRecords);
        model.addAttribute("dropRecords",dropRecords);

        model.addAttribute("reserveSum",reserveSum);
        model.addAttribute("unReserveSum",unReserveSum);
        model.addAttribute("enrollSum",enrollSum);
        model.addAttribute("dropSum",dropSum);

        return "/organization/stats_finance";


    }



}
