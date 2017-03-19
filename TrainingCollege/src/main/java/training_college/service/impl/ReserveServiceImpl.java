package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training_college.entity.*;
import training_college.repository.*;
import training_college.service.ReserveService;
import training_college.util.DisCntHelper;
import training_college.util.IDHelper;
import training_college.util.enumeration.PayMethod;
import training_college.util.enumeration.SelectMethod;
import training_college.util.enumeration.UserType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ss14 on 2017/3/15.
 */

@Service
@Transactional
public class ReserveServiceImpl implements ReserveService {

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
    DropRecordRepository dropRecordRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    DisCntHelper disCntHelper;
    @Autowired
    IDHelper idHelper;

    @PersistenceContext
    EntityManager em;


    @Override
    public boolean reserve(int sid, int pid) {
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


        //S2: 添加reservation记录

        Reservation reservation = new Reservation();
        reservation.setProject(project);
        reservation.setStudent(student);
        reservation.setPayment(payment);
        reservationRepository.save(reservation);

        //S3: 添加project_student记录
        ProjectStudent projectStudent = new ProjectStudent();
        projectStudent.setSid(sid);
        projectStudent.setPid(pid);
        projectStudentRepository.save(projectStudent);

        //S4: curStdCnt++
        int curStdCnt = project.getCurStdCnt();
        project.setCurStdCnt(++curStdCnt);

        //S5 添加入学记录
        EnrollmentRecord enrollRecord = new EnrollmentRecord();
        String orgSysId = idHelper.validateId(project.getOrganization().getId());
        String proName = project.getClassName();
        String stdName = student.getName();

        SelectMethod selectMethod = SelectMethod.reserve;
        UserType userType = UserType.member;
        PayMethod payMethod = PayMethod.card;

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
    public boolean unreserve(int reservationId) {

        Reservation reservation = reservationRepository.findOne(reservationId);

        Student student = reservation.getStudent();
        Project project = reservation.getProject();

        //S1: 还款

        int payment = reservation.getPayment();
        int balance = student.getCard().getBalance();
        student.getCard().setBalance(balance+payment);
        cardRepository.saveAndFlush(student.getCard());


        //S2: reservation记录设为取消状态

        reservation.setCanceled(true);
        reservationRepository.saveAndFlush(reservation);

        //S3: 删除一条project_student记录
        ProjectStudentPK pk = new ProjectStudentPK();
        pk.setPid(project.getId());
        pk.setSid(student.getId());
        projectStudentRepository.delete(pk);

        //S4: curStdCnt--
        int curStdCnt = project.getCurStdCnt();
        project.setCurStdCnt(--curStdCnt);
        projectRepository.saveAndFlush(project);

        //S5 删除入学记录
        EnrollmentRecord enrollmentRecord =
                enrollRecordRepository.getByProjectNameAndStudentName(project.getClassName(),student.getName());
        enrollRecordRepository.delete(enrollmentRecord);
        return true;
    }

    @Override
    public List<Reservation> getNotStartedReservationBySid(int sid) {
        return reservationRepository.getNotStartedReservationBySid(sid);
    }

    @Override
    public List<Reservation> getReservationBySid(int sid) {

        return reservationRepository.getByStudent_IdOrderByDateDesc(sid);
    }

    @Override
    public List<Reservation> getUnReservationBySid(int sid) {
        return reservationRepository.getUnReservationsBySid(sid);
    }
}
