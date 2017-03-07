package training_college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ss14 on 2017/3/7.
 */
@Controller

public class LoginController {

    @RequestMapping(value={"/"} , method = RequestMethod.GET)
    public String  getLoginPage(){

        return "login";

    }


}
