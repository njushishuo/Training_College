package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.*;
import training_college.repository.*;
import training_college.service.RecordService;
import training_college.util.enumeration.PayMethod;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ss14 on 2017/3/14.
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    EnrollRecordRepository enrollRecordRepository;
    @Autowired
    DropRecordRepository dropRecordRepository;
    @Autowired
    GradeRecordRepository gradeRecordRepository;
    @Autowired
    ProjectRepository projectRepository;


    public String validateId(int orgId){
        String id = String.valueOf(orgId);
        while(id.length()<7){
            id= '0'+id;
        }
        return id;
    }

    @Override
    public List<GradeRecord> getGradeRecordsByStdName(String name) {
        return gradeRecordRepository.getByStudentName(name);
    }

    @Override
    public List<Project> getAvaliableProjectsByOrgId(int id) {

        return projectRepository.getAvaliableProjectsByOrgId(id);
    }

    @Override
    public List<Project> getSelectedProjectsByOrgId(int id) {
        return projectRepository.getSelectedProjectsByOrgId(id);
    }

    @Override
    public List<EnrollmentRecord> getAllEnrollRecordsByOrgId(int id) {

        return enrollRecordRepository.getByOrgSystemIdOrderByDateDesc( validateId(id));
    }

    @Override
    public List<EnrollmentRecord> getEnrollRecordsWithSelectionByStdName(String name) {
        return enrollRecordRepository.getEnrollRecordsWithSelectionByStdName(name);
    }


    @Override
    @Transactional
    public void nonMemberEnroll(EnrollmentRecord enrollmentRecord, Organization organization) {
        //S1：机构收钱
        int balance = organization.getBalance();
        int payment = enrollmentRecord.getPayment();
        organization.setBalance(balance+payment);
        organizationRepository.saveAndFlush(organization);
        //S2：添加记录
        enrollRecordRepository.save(enrollmentRecord);
        //S3：加人数
        Project project = projectRepository.getByClassName(enrollmentRecord.getProjectName());
        int curStdCnt = project.getCurStdCnt();
        project.setCurStdCnt(++curStdCnt);

    }

    @Override
    public List<DropRecord> getAllDropRecordsByOrgId(int id) {
        return dropRecordRepository.getByOrgSystemIdOrderByDateDesc(validateId(id));
    }

    @Override
    public List<DropRecord> getAllDropRecordByStdName(String name) {
        return dropRecordRepository.getByStudentNameOrderByDateDesc(name);
    }


    @Override
    @Transactional
    public void nonMemberDrop(DropRecord dropRecord , Organization organization) {
        //S1：机构退钱
        int balance = organization.getBalance();
        int repayment = dropRecord.getPayment();
        organization.setBalance(balance-repayment);
        //S2：增加记录
        dropRecordRepository.save(dropRecord);
        //S3：减少人数
        Project project = projectRepository.getByClassName(dropRecord.getProjectName());
        int curStdCnt = project.getCurStdCnt();
        project.setCurStdCnt(--curStdCnt);

    }


    @Override
    public List<GradeRecord> getAllGradeRecordsByOrgId(int id) {
        return gradeRecordRepository.getByOrgSystemId(validateId(id));
    }

    @Override
    public void addGradeRecord(GradeRecord gradeRecord) {
        gradeRecordRepository.save(gradeRecord);
    }
}
