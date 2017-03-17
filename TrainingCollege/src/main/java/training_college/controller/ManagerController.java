package training_college.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import training_college.entity.Project;
import training_college.service.ClassInfoService;
import training_college.service.ExamService;

import java.util.List;
import java.util.Map;

/**
 * Created by ss14 on 2017/3/17.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ExamService examService;
    @Autowired
    ClassInfoService classInfoService;


/****************************************审批***************************************************/

    @RequestMapping(value = "/examination/creation" , method = RequestMethod.GET)
    public String getExaminationCreationPage(Model model){

        List<Project> pendingProjects = examService.getPendingNewProjects();
        List<Project> processedProjects = examService.getAllProcessedNewProjects();

        Map pendingMap  =  classInfoService.getNewCourseMapByProejcts(pendingProjects);
        Map processedMap  =  classInfoService.getNewCourseMapByProejcts(processedProjects);

        model.addAttribute("pendingProjects",pendingProjects);
        model.addAttribute("pendingMap", pendingMap);
        model.addAttribute("processedProjects",processedProjects);
        model.addAttribute("processedMap", processedMap);

        return "/manager/examination_creation";
    }


    @ResponseBody
    @RequestMapping(value = "/examination/creation/approval" , method = RequestMethod.POST)
    public boolean approveCreation(HttpServletRequest request){

        int pid = Integer.parseInt(request.getParameter("projectId"));
        return examService.approveNewProject(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/examination/creation/rejection" , method = RequestMethod.POST)
    public boolean rejectCreation(HttpServletRequest request){

        int pid = Integer.parseInt(request.getParameter("projectId"));
        return examService.rejectNewProject(pid);

    }



    @RequestMapping(value = "/examination/modification" , method = RequestMethod.GET)
    public String getExaminationModificationPage(Model model){

        List<Project> pendingProjects = examService.getPendingNewProjects();
        List<Project> processedProjects = examService.getAllProcessedNewProjects();

        Map pendingMap  =  classInfoService.getNewCourseMapByProejcts(pendingProjects);
        Map processedMap  =  classInfoService.getNewCourseMapByProejcts(processedProjects);

        model.addAttribute("pendingProjects",pendingProjects);
        model.addAttribute("pendingMap", pendingMap);
        model.addAttribute("processedProjects",processedProjects);
        model.addAttribute("processedMap", processedMap);

        return "/manager/examination_modification";
    }

    @RequestMapping(value = "/examination/modification/{pid}" , method = RequestMethod.POST)
    public String examineModification(){
        return "/manager/examination_modification";
    }


/****************************************结账***************************************************/

    @RequestMapping(value = "/accountSettlement/payment" , method = RequestMethod.GET)
    public String getAccountSettlementPaymentPage(){
        return "/manager/account_payment";
    }

    @RequestMapping(value = "/accountSettlement/repayment" , method = RequestMethod.GET)
    public String getAccountSettlementRepaymentPage(){
        return "/manager/account_repayment";
    }



/****************************************结账***************************************************/


    @RequestMapping(value = "/statistics/recruit" , method = RequestMethod.GET)
    public String getStatsRecruitPage(){
        return "/manager/stats_recruit";
    }

    @RequestMapping(value = "/statistics/study" , method = RequestMethod.GET)
    public String getStasStudyPage(){
        return "/manager/stats_study";
    }

    @RequestMapping(value = "/statistics/finance" , method = RequestMethod.GET)
    public String getStasFinancePage(){
        return "/manager/stats_finance";
    }




}
