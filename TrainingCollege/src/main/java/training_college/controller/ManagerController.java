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
import training_college.service.SettleService;
import training_college.service.StatsService;
import training_college.vo.OrgFinanceVO;
import training_college.vo.OrgRecruitVO;
import training_college.vo.OrgStudyVO;

import java.util.HashMap;
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
    SettleService settleService;
    @Autowired
    ClassInfoService classInfoService;
    @Autowired
    StatsService statsService;



/****************************************审批***************************************************/

    @RequestMapping(value = "/examination/creation" , method = RequestMethod.GET)
    public String getExaminationCreationPage(Model model){

        List<Project> pendingProjects = examService.getPendingNewProjects();
        List<Project> processedProjects = examService.getAllProcessedNewProjects();

        //从newschedule中读取待审核的班级的课程信息
        Map pendingMap  =  classInfoService.getNewCourseMapByProejcts(pendingProjects);
        //从newschedule中读取处理过的班级的课程信息
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

        List<Project> pendingProjects = examService.getPendingModifyProjects();
        List<Project> processedProjects = examService.getAllProcessedModifyProjects();

        //从postchedule中读取待审核的班级的课程信息
        Map pendingMap  =  classInfoService.getPostModifyCourseMapByProejcts(pendingProjects);
        //从postchedule中读取处理过的班级的课程信息
        Map processedMap  =  classInfoService.getPostModifyCourseMapByProejcts(processedProjects);

        model.addAttribute("pendingProjects",pendingProjects);
        model.addAttribute("pendingMap", pendingMap);
        model.addAttribute("processedProjects",processedProjects);
        model.addAttribute("processedMap", processedMap);

        return "/manager/examination_modification";
    }

    @ResponseBody
    @RequestMapping(value = "/examination/modification/approval" , method = RequestMethod.POST)
    public boolean approveModification(HttpServletRequest request){

        int pid = Integer.parseInt(request.getParameter("projectId"));
        return examService.approveModifyProject(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/examination/modification/rejection" , method = RequestMethod.POST)
    public boolean rejectModification(HttpServletRequest request){

        int pid = Integer.parseInt(request.getParameter("projectId"));
        return examService.rejectModifyProject(pid);

    }


/****************************************结账***************************************************/

    //结算界面
    @RequestMapping(value = "/accountSettlement" , method = RequestMethod.GET)
    public String getAccountSettlementPaymentPage(Model model){


        List<String> orgSysIds  = settleService.getPaymentUncheckedOrgSystemIds();
        int companyBalance = settleService.getCompanyBalance();

        model.addAttribute("orgSysIds",orgSysIds);
        model.addAttribute("balance",companyBalance);
        return "/manager/account_settle";
    }


    //AJAX 获取某个机构的收支
    @ResponseBody
    @RequestMapping(value = "/accountSettlement/profitStats" , method = RequestMethod.GET)
    public int [] getProfitStatsOfOrg(HttpServletRequest request){

        String sysId = request.getParameter("OrgSysId");
        int income = settleService.getPaymentSumByOrgSysId(sysId);
        int output = settleService.getRepaymentSumBySysOrgId(sysId);
        int profit = income - output;
        int [] result = {income,output,profit};
        return result;
    }


    //AJAX 结算POST
    @ResponseBody
    @RequestMapping(value = "/accountSettlement/settleRequest" , method = RequestMethod.POST)
    public boolean settlePaymentOfOrg(HttpServletRequest request){

        String sysId = request.getParameter("orgSysId");
        int profit = Integer.parseInt(request.getParameter("profit"));

        return  settleService.settleByOrgSysId(sysId,profit);

    }



/****************************************统计***************************************************/


    @RequestMapping(value = "/statistics/recruit" , method = RequestMethod.GET)
    public String getStatsRecruitPage(Model model){

        //获取所有机构的招生情况，即每个机构的预订数、退订数、入学人数、退学人数、当前人数统计
        List<OrgRecruitVO> orgRecruitVOs =statsService.getAllOrgRecruitVO();
        model.addAttribute("orgRecruitVOs",orgRecruitVOs);

        return "/manager/stats_recruit";
    }

    @RequestMapping(value = "/statistics/study" , method = RequestMethod.GET)
    public String getStasStudyPage(Model model){

        List<OrgStudyVO> orgStudyVOs = statsService.getAllOrgStudyVO();
        model.addAttribute("orgStudyVOs",orgStudyVOs);
        return "/manager/stats_study";

    }

    @RequestMapping(value = "/statistics/finance" , method = RequestMethod.GET)
    public String getStasFinancePage(Model model){

        List<OrgFinanceVO> orgStudyVOs = statsService.getAllOrgFinanceVO();
        model.addAttribute("orgFinanceVOs",orgStudyVOs);
        return "/manager/stats_finance";

    }




}
