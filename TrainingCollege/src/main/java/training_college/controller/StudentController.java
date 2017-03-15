package training_college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ss14 on 2017/3/15.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @RequestMapping(value = "/classInfo" , method = RequestMethod.GET)
    public String getClassInfoPage(){


        return "/student/class_info";

    }


}
