package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training_college.entity.*;
import training_college.repository.*;
import training_college.service.SelectService;
import training_college.util.DisCntHelper;
import training_college.util.IDHelper;
import training_college.util.enumeration.PayMethod;
import training_college.util.enumeration.SelectMethod;
import training_college.util.enumeration.UserType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ss14 on 2017/3/15.
 */

@Service
@Transactional
public class SelectServiceImpl implements SelectService {

    @Autowired
    CardRepository cardRepository;
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
    DisCntHelper disCntHelper;
    @Autowired
    IDHelper idHelper;


    @PersistenceContext
    EntityManager em;


    @Override
    public boolean select(int sid, int pid) {
        Student student = studentRepository.getOne(sid);
        Project project = projectRepository.getOne(pid);

        //S1: 扣款
        int balance = student.getCard().getBalance();
        int price = project.getTotalPrice();

        if( balance<price ){
            return false;
        }
        student.getCard().setBalance(balance - price);
        cardRepository.saveAndFlush(student.getCard());

        //S2: 添加project_student记录
        ProjectStudent projectStudent = new ProjectStudent();
        projectStudent.setSid(sid);
        projectStudent.setPid(pid);
        projectStudentRepository.saveAndFlush(projectStudent);

        //S3: curStdCnt++
        int curStdCnt = project.getCurStdCnt();
        project.setCurStdCnt(++curStdCnt);
        projectRepository.saveAndFlush(project);

        //S4 添加入学记录
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

        em.persist(enrollRecord);
        em.flush();
        em.clear();
        return true;
    }


    @Override
    public boolean unselect(int sid, int pid) {
        Student student = studentRepository.getOne(sid);
        Project project = projectRepository.getOne(pid);

        //S1: 还款
        int balance = student.getCard().getBalance();
        int price = project.getTotalPrice();

        //TODO计算还款
        student.getCard().setBalance(balance + price);
        cardRepository.saveAndFlush(student.getCard());


        //S2: 删除一条project_student记录
        ProjectStudentPK pk = new ProjectStudentPK();
        pk.setPid(project.getId());
        pk.setSid(student.getId());
        projectStudentRepository.delete(pk);

        //S3: curStdCnt--
        int curStdCnt = project.getCurStdCnt();
        project.setCurStdCnt(--curStdCnt);
        projectRepository.saveAndFlush(project);

        //S4 添加退学记录
        DropRecord dropRecord = new DropRecord();
        String orgSysId = idHelper.validateId(project.getOrganization().getId());
        String proName = project.getClassName();
        String stdName = student.getName();


        int totalPrice = project.getTotalPrice();
        int payment = (int)(totalPrice*0.5);

        SelectMethod selectMethod = SelectMethod.reserve;
        UserType userType = UserType.member;
        PayMethod payMethod = PayMethod.card;

        dropRecord.setOrgSystemId(orgSysId);
        dropRecord.setProjectName(proName);
        dropRecord.setStudentName(stdName);
        dropRecord.setPrice(totalPrice);
        dropRecord.setPayment(payment);
        dropRecord.setPayMethod(payMethod);
        dropRecord.setUserType(userType);
        dropRecord.setSelectMethod(selectMethod);

        em.persist(dropRecord);
        em.flush();
        em.clear();
        return true;
    }
}