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


    private final double repayRate = 0.5;


    @Override
    public boolean select(int sid, int pid) {
        Student student = studentRepository.getOne(sid);
        Project project = projectRepository.getOne(pid);

        //S1: 扣款,更新积分和等级
        int balance = student.getCard().getBalance();
        int price = project.getTotalPrice();
        double disCnt = disCntHelper.getDisCntByLevel(student.getCard().getLevel());
        int payment = (int) (price* disCnt);

        if( balance<payment ){
            return false;
        }
        student.getCard().setBalance(balance - payment);


        int score = student.getCard().getScore();
        score+=200;
        student.getCard().setScore(score);
        student.getCard().setLevel(disCntHelper.getLevelByScore(score));

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
        SelectMethod selectMethod = SelectMethod.select;

        enrollRecord.setOrgSystemId(orgSysId);
        enrollRecord.setProjectName(proName);
        enrollRecord.setStudentName(stdName);
        enrollRecord.setPrice(price);
        enrollRecord.setPayment(payment);
        enrollRecord.setUserType(userType);
        enrollRecord.setPayMethod(payMethod);
        enrollRecord.setSelectMethod(selectMethod);
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
        int payment = (int)(price*repayRate);

        student.getCard().setBalance(balance+payment);
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

        SelectMethod selectMethod = SelectMethod.reserve;
        UserType userType = UserType.member;
        PayMethod payMethod = PayMethod.card;

        dropRecord.setOrgSystemId(orgSysId);
        dropRecord.setProjectName(proName);
        dropRecord.setStudentName(stdName);
        dropRecord.setPrice(price);
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
