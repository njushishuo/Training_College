package training_college.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import training_college.entity.Student;
import training_college.service.RegisterService;

/**
 * Created by ss14 on 2017/3/11.
 */
@Controller
@RequestMapping("/registeration")
public class RegisterController {

    @Autowired
    RegisterService registerService;


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public boolean studentRegister(HttpServletRequest request){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if(role.equals("student")){
            return  registerService.stdRegister(username,password);
        }else if(role.equals("organization")){
            return  registerService.orgRegister(username,password);
        }

        return false;
    }
}
