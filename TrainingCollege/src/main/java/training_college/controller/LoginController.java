package training_college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import training_college.entity.Company;
import training_college.entity.Organization;
import training_college.entity.Student;
import training_college.service.LoginService;
import training_college.util.enumeration.LoginResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ss14 on 2017/3/7.
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;


    @RequestMapping(value={"/login"} , method = RequestMethod.GET)
    public String  getLoginPage(){

        return "/auth/login";

    }

    @RequestMapping(value={"/login"} , method = RequestMethod.POST)
    public String  Login(HttpServletRequest request , HttpSession session ){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");


        LoginResult result;
        if (role.equals("student")){

            result = loginService.studentLogin(username,password);
            if(result==LoginResult.pass){
                Student student = loginService.getStudentByUsername(username);
                session.setAttribute("student",student);
                return  "redirect:/student/"+student.getId()+"/classInfo";
            }
        }
        else if(role.equals("organization")){

            result = loginService.organizationLogin(username,password);
            if(result== LoginResult.pass){
                Organization organization = loginService.getOrganizationByUsername(username);
                session.setAttribute("organization",organization);
                return "redirect:/organization/"+organization.getId()+"/classInfo";
            }

        }else{

            result = loginService.managerLogin(username,password);
            if(result== LoginResult.pass){
                Company company = loginService.getCompanyByUsername(username);
                session.setAttribute("company",company);
                return "redirect:/manager/examination/creation";
            }

        }

        return "redirect:/login";


    }


}
