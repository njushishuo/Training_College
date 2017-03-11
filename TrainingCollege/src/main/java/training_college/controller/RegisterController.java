package training_college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import training_college.entity.Student;

/**
 * Created by ss14 on 2017/3/11.
 */
@Controller
@RequestMapping("/registeration")
public class RegisterController {

    @RequestMapping(value= "/student"  , method = RequestMethod.GET)
    public String getRegisterPageForStudent(){
        return "register_std";
    }

    @RequestMapping(value="/organization" , method = RequestMethod.GET)
    public String getRegisterPageForOrganization(){
        return "register_org";
    }

    @RequestMapping(value="/student" , method = RequestMethod.POST)
    public String studentRegister(Student student){
        System.out.print(student.getName()+student.getUsername()+student.getPassword()+student.getGender().toString());
        return  "redirect: /login";
    }

    @RequestMapping(value="/organization" , method = RequestMethod.POST)
    public String organizationRegister(){
        return "redirect: /login";
    }
}
