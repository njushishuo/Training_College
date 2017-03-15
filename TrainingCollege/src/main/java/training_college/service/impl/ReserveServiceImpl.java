package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.*;
import training_college.repository.*;
import training_college.service.ReserveService;
import training_college.util.IDHelper;
import training_college.util.enumeration.PayMethod;
import training_college.util.enumeration.UserType;

import javax.transaction.Transactional;

/**
 * Created by ss14 on 2017/3/15.
 */

@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectStudentRepository projectStudentRepository;
    @Autowired
    EnrollRecordRepository enrollRecordRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    IDHelper idHelper;


    @Override
    @Transactional
    public boolean reserve(int sid, int pid) {
        Student student = studentRepository.getOne(sid);
        Project project = projectRepository.getOne(pid);

        //S1: 扣款
        int balance = student.getCard().getBalance();
        int price = project.getTotalPrice();

        if( balance<price ){
            return false;
        }
        student.getCard().setBalance(balance - price);
        //S2: 添加reservation记录
        Reservation reservation = new Reservation();
        reservation.setProject(project);
        reservation.setStudent(student);
        reservationRepository.save(reservation);

        //S3: 添加project_student记录
        ProjectStudent projectStudent = new ProjectStudent();
        projectStudent.setSid(sid);
        projectStudent.setPid(pid);
        projectStudentRepository.save(projectStudent);

        //S4: curStdCnt++
        int curStdCnt = project.getCurStdCnt();
        project.setCurStdCnt(++curStdCnt);

        //S5
        EnrollmentRecord enrollRecord = new EnrollmentRecord();
        String orgSysId = idHelper.validateId(project.getOrganization().getId());
        String proName = project.getClassName();
        String stdName = student.getName();
        UserType userType = UserType.member;
        PayMethod payMethod = PayMethod.card;

        enrollRecord.setOrgSystemId(orgSysId);
        enrollRecord.setProjectName(proName);
        enrollRecord.setStudentName(stdName);
        enrollRecord.setUserType(userType);
        enrollRecord.setPayMethod(payMethod);

        enrollRecordRepository.save(enrollRecord);

        return true;
    }
}
