package training_college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by ss14 on 2017/3/19.
 */
@Controller
public class LogoutController {

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){

        session.invalidate();
        return "redirect:/login";
    }



}
