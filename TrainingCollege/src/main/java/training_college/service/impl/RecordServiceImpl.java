package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.DropRecord;
import training_college.entity.EnrollmentRecord;
import training_college.entity.GradeRecord;
import training_college.entity.Project;
import training_college.repository.DropRecordRepository;
import training_college.repository.EnrollRecordRepository;
import training_college.repository.GradeRecordRepository;
import training_college.repository.ProjectRepository;
import training_college.service.RecordService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ss14 on 2017/3/14.
 */
@Service
public class RecordServiceImpl implements RecordService {

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

        return enrollRecordRepository.getByOrgSystemId( validateId(id));
    }

    @Override
    public List<EnrollmentRecord> getEnrollRecordsWithSelectionByStdName(String name) {
        return enrollRecordRepository.getEnrollRecordsWithSelectionByStdName(name);
    }

    @Override
    @Transactional
    public void addEnrollRecordAndIncCurStdCnt(EnrollmentRecord enrollmentRecord) {

        enrollRecordRepository.save(enrollmentRecord);
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
    public void addDropRecordAndDecCurStdCnt(DropRecord dropRecord) {

        dropRecordRepository.save(dropRecord);
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
