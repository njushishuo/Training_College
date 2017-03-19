package training_college.service;

import training_college.entity.Company;
import training_college.entity.Organization;
import training_college.entity.Student;
import training_college.util.enumeration.LoginResult;

/**
 * Created by ss14 on 2017/3/13.
 */
public interface LoginService {

    Student getStudentByUsername(String username);

    Organization getOrganizationByUsername(String username);

    Company getCompanyByUsername(String username);

    LoginResult studentLogin(String username , String password);

    LoginResult organizationLogin(String username , String password);

    LoginResult managerLogin(String username , String password);



}
