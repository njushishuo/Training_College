package training_college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ss14 on 2017/3/11.
 */
@Controller

public class OrganizationController {
    @RequestMapping(value = "/organization" , method = RequestMethod.GET)
    public String getOrganizationHomePage(){
        return "home_org";
    }

}
