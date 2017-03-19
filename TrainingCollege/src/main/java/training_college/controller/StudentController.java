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
import training_college.util.DateHelper;
import training_college.util.DisCntHelper;
import training_college.util.IDHelper;
import training_college.vo.CardVO;
import training_college.vo.ClassInfoVO;

import javax.servlet.http.HttpSession;
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
    ReserveService reserveService;
    @Autowired
    SelectService selectService;
    @Autowired
    RecordService recordService;
    @Autowired
    CardService cardService;
    @Autowired
    ReChargeService reChargeService;


    @Autowired
    ConsumptionService consumptionService;

    @Autowired
    IDHelper idHelper;
    @Autowired
    DateHelper dateHelper;
    @Autowired
    DisCntHelper disCntHelper;

/****************************************预订、选课************************************************************/
    @RequestMapping(value = "/{id}/classInfo" , method = RequestMethod.GET)
    public String getClassInfoPage(@PathVariable int id,Model model){

        List<Project> projects = classInfoService.getAvaliableProjectsNotSelectedByStdId(id);

        ClassInfoVO classInfoVO= classInfoService.getClassInfoVOByProjects(projects);
        model.addAttribute("projectVOs",classInfoVO.projectVOList);
        model.addAttribute("courseMap", classInfoVO.courseMap);

        return "/student/class_info";

    }

    @ResponseBody
    @RequestMapping(value = "/{id}/reservation/{pid}" , method = RequestMethod.POST )
    public boolean reserveProject(@PathVariable int id,@PathVariable int pid){


        return reserveService.reserve(id,pid);

    }

    @ResponseBody
    @RequestMapping(value = "/{id}/selection/{pid}" , method = RequestMethod.POST )
    public boolean selectProject(@PathVariable int id,@PathVariable int pid){

        return selectService.select(id,pid);

    }


/************************************************我的会员卡****************************************************/
    @RequestMapping(value = "/{id}/cardInfo" , method = RequestMethod.GET )
    public String getCardInfoPage(@PathVariable int id , Model model){

        Student student = cardService.getStudentById(id);
        List<BankCard> bankCards = cardService.getBankCardsBySid(id);
        Card card = student.getCard();
        CardVO cardVO = new CardVO();
        cardVO.id = idHelper.validateId(id);
        cardVO.card = card;
        cardVO.disCnt = disCntHelper.getDisCntByLevel(card.getLevel());
        BankCard usingBankCard = cardService.getBankCardById(card.getBankCardId());

        model.addAttribute("student", student);
        model.addAttribute("cardVO",cardVO);
        model.addAttribute("bankCards",bankCards);
        model.addAttribute("curBankCardBalance",usingBankCard.getBalance());

        return "student/card_info";

    }

    @RequestMapping(value = "/{id}/cardInfo" , method = RequestMethod.POST )
    public String updateCardInfo(@PathVariable int id , HttpServletRequest request){

        //先更新student
        String name = (String) request.getParameter("studentName");
        String email = (String) request.getParameter("email");
        String phone = (String) request.getParameter("phone");

        Student student = cardService.getStudentById(id);
        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);
        cardService.saveStudent(student);

        //再更新card
        int bank_card_id = Integer.parseInt(request.getParameter("bankCardRadio")) ;
        Card card = student.getCard();
        card.setBankCardId(bank_card_id);
        cardService.saveCard(card);
        return "redirect:/student/"+id+"/cardInfo";

    }


    @ResponseBody
    @RequestMapping(value = "/{id}/reCharge" , method = RequestMethod.POST)
    public boolean recharge(@PathVariable int id , HttpServletRequest request , HttpSession session){

        Student student  = (Student) session.getAttribute("student");
        Card card =student.getCard();
        String status = request.getParameter("status");
        String reChargeNumString = request.getParameter("rechargeNum");

        return reChargeService.reCharge(card,reChargeNumString,status);
    }


/************************************************我的订单****************************************************/
    @RequestMapping(value = "/{id}/orders" , method = RequestMethod.GET )
    public String getOrdersPage(@PathVariable int id , Model model){

        //获取真正出于预订状态的班级，这样退订只能发生在开学前
        List<Reservation> reservations = reserveService.getNotStartedReservationBySid(id);
        model.addAttribute("reservations",reservations);

        return "student/orders";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/orders" , method = RequestMethod.POST )
    public boolean unReserve(@PathVariable int id ,HttpServletRequest request, Model model){

        String [] reservationIds = request.getParameterValues("ridCheckBox");

        for(int i=0; i<reservationIds.length;i++){

            int rid = Integer.parseInt(reservationIds[i]);
            System.err.println(rid);
            if(!reserveService.unreserve(rid)){
                return false;
            }
        }
        return true;
    }

/************************************************我的班级****************************************************/

    @RequestMapping(value = "/{id}/projects" , method = RequestMethod.GET )
    public String getProjectsPage(@PathVariable int id , Model model){
        //获取所有用户已加入的班级并且这些班级已经开始上课，这些班级中可能是经过预订的，也可能是开学后选课添加的。
        //这样保证了退课只能发生在开学后
        List<Project> projects = classInfoService.getStartedProjectsByStdId(id);
        ClassInfoVO classInfoVO= classInfoService.getClassInfoVOByProjects(projects);
        model.addAttribute("projectVOs",classInfoVO.projectVOList);
        model.addAttribute("courseMap", classInfoVO.courseMap);
        return "/student/projects";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/unSelection/{pid}" , method = RequestMethod.POST )
    public boolean unSelect(@PathVariable int id ,@PathVariable int pid){

        return selectService.unselect(id,pid);
    }


/************************************************我的成绩****************************************************/

    @RequestMapping(value = "/{id}/grades" , method = RequestMethod.GET )
    public String getGradesPage(@PathVariable int id , Model model){

        Student student = cardService.getStudentById(id);
        List<GradeRecord> gradeRecords = recordService.getGradeRecordsByStdName(student.getName());

        model.addAttribute("gradeRecords" , gradeRecords);
        return "/student/grades";
    }

/************************************************我的消费****************************************************/


    @RequestMapping(value = "/{id}/consumption" , method = RequestMethod.GET )
    public String getConsumptionPage(@PathVariable int id , Model model){

        Student student =cardService.getStudentById(id);

        //首先获取所有预订记录用于计算预订花销
        List<Reservation> reservations = reserveService.getReservationBySid(id);
        //获取所有退订记录用于计算退订收入
        List<Reservation> unReservations = reserveService.getUnReservationBySid(id);
        //获取所有选课记录用于计算选课花销
        List<EnrollmentRecord> enrollmentRecords =
                recordService.getEnrollRecordsWithSelectionByStdName(student.getName());
        //获取所有退课记录用于计算退课收入
        List<DropRecord> dropRecords = recordService.getAllDropRecordByStdName(student.getName());

        int reserveSum = consumptionService.getSumByReservations(reservations);
        int unReserveSum = consumptionService.getSumByUnReservations(reservations);
        int enrollSum = consumptionService.getSumByEnrollments(enrollmentRecords);
        int dropSum = consumptionService.getSumByDropRecords(dropRecords);

        model.addAttribute("reservations" , reservations);
        model.addAttribute("unReservations" , unReservations);
        model.addAttribute("enrollRecords" , enrollmentRecords);
        model.addAttribute("dropRecords" , dropRecords);
        model.addAttribute("reserveSum" , reserveSum);
        model.addAttribute("unReserveSum" , unReserveSum);
        model.addAttribute("enrollSum" , enrollSum);
        model.addAttribute("dropSum" , dropSum);


        return "/student/consumption";
    }


}
