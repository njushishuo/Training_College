package training_college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import training_college.entity.Card;
import training_college.entity.Course;
import training_college.entity.Project;
import training_college.entity.Student;
import training_college.repository.StudentRepository;
import training_college.service.CardService;
import training_college.service.ClassInfoService;
import training_college.service.ReserveService;
import training_college.service.SelectService;
import training_college.util.DateHelper;
import training_college.util.DisCntHelper;
import training_college.util.IDHelper;
import training_college.vo.CardVO;
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
    ReserveService reserveService;
    @Autowired
    SelectService selectService;
    @Autowired
    CardService cardService;

    @Autowired
    IDHelper idHelper;
    @Autowired
    DateHelper dateHelper;
    @Autowired
    DisCntHelper disCntHelper;


    @RequestMapping(value = "/{id}/classInfo" , method = RequestMethod.GET)
    public String getClassInfoPage(@PathVariable int id,Model model){

        List<Project> projects = classInfoService.getAvaliableProjectsNotSelectedByStdId(id);
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

    @RequestMapping(value = "/{id}/reservation/{pid}" , method = RequestMethod.POST )
    public boolean reserveProject(@PathVariable int id,@PathVariable int pid){


        return reserveService.reserve(id,pid);

    }

    @RequestMapping(value = "/{id}/selection/{pid}" , method = RequestMethod.POST )
    public boolean selectProject(@PathVariable int id,@PathVariable int pid){

        return selectService.select(id,pid);

    }

    @RequestMapping(value = "/{id}/card" , method = RequestMethod.GET )
    public String getCardPage(@PathVariable int id , Model model){

        Student student = cardService.getStudentById(id);
        Card card = student.getCard();
        CardVO cardVO = new CardVO();
        cardVO.id = idHelper.validateId(id);
        cardVO.card = card;
        cardVO.disCnt = disCntHelper.getDisCntByLevel(card.getLevel());

        model.addAttribute("student", student);
        model.addAttribute("cardVO",cardVO);

        return "student/card";

    }

}
