package training_college.service;

import training_college.entity.DropRecord;
import training_college.entity.EnrollmentRecord;
import training_college.entity.GradeRecord;
import training_college.entity.Project;

import java.util.List;

/**
 * Created by ss14 on 2017/3/14.
 */
public interface RecordService {

    /**
     * 获取那些 开放且人数未满的班级
     * @param id
     * @return
     */
    List<Project> getAvaliableProjectsByOrgId(int id);


    /**
     * 获取那些 开放且人数>0的班级
     * @param id
     * @return
     */
    List<Project> getSelectedProjectsByOrgId(int id);

    /**
     * 获取一个机构的全部入学记录
     * @return
     */
    List<EnrollmentRecord> getAllEnrollRecordsByOrgId(int id);


    /**
     * 获取一个会员的入学记录（通过非预订的方式）
     * @param name
     * @return
     */
    List<EnrollmentRecord> getEnrollRecordsWithSelectionByStdName(String name);


    /**
     * 添加入学记录，并增加相应project的当前人数
     * @return
     */
    void addEnrollRecordAndIncCurStdCnt(EnrollmentRecord enrollmentRecord);


    /**
     * 获取一个机构的全部退课记录
     * @return
     */
    List<DropRecord> getAllDropRecordsByOrgId(int id);

    /**
     * 获取一个会员的全部退课记录
     * @param name
     * @return
     */
    List<DropRecord> getAllDropRecordByStdName(String name);

    /**
     * 添加退课（退学）记录，并增加相应project的当前人数
     * @return
     */
    void addDropRecordAndDecCurStdCnt(DropRecord dropRecord);


    /**
     * 获取一个机构的全部成绩记录
     * @return
     */
    List<GradeRecord> getAllGradeRecordsByOrgId(int id);

    void addGradeRecord(GradeRecord gradeRecord);

    /**
     * 将数据库ID转化为系统的7位ID
     * @param id
     * @return
     */
    String validateId(int id);


    List<GradeRecord> getGradeRecordsByStdName(String name);






}
