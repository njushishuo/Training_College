package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.Company;
import training_college.entity.Organization;
import training_college.entity.Student;
import training_college.repository.CompanyRepository;
import training_college.repository.OrganizationRepository;
import training_college.repository.StudentRepository;
import training_college.service.LoginService;
import training_college.util.enumeration.LoginResult;


/**
 * Created by ss14 on 2017/3/13.
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CompanyRepository companyRepository;


    @Override
    public Student getStudentByUsername(String username) {

        return studentRepository.findByUsername(username);
    }

    @Override
    public Organization getOrganizationByUsername(String username) {

        return organizationRepository.findByUsername(username);
    }

    @Override
    public Company getCompanyByUsername(String username) {
        return companyRepository.findByUsername(username);
    }

    @Override
    public LoginResult studentLogin(String username , String password) {

        Student student = studentRepository.findByUsername(username);
        if(student == null){
            return LoginResult.not_exist;
        }else if(password.equals(student.getPassword())){
            return LoginResult.pass;
        }else{
            return LoginResult.failed;
        }

    }

    @Override
    public LoginResult organizationLogin(String username , String password) {

        Organization organization = organizationRepository.findByUsername(username);
        if(organization == null){
            return LoginResult.not_exist;
        }else if(password.equals(organization.getPassword())){
            return LoginResult.pass;
        }else{
            return LoginResult.failed;
        }

    }

    @Override
    public LoginResult managerLogin(String username, String password) {
        Company company = companyRepository.findByUsername(username);
        if(company == null){
            return LoginResult.not_exist;
        }else if(password.equals(company.getPassword())){
            return LoginResult.pass;
        }else{
            return LoginResult.failed;
        }
    }
}
