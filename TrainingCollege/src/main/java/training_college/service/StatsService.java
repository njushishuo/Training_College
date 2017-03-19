package training_college.service;

import training_college.entity.DropRecord;
import training_college.entity.EnrollmentRecord;
import training_college.entity.Reservation;
import training_college.vo.OrgFinanceVO;
import training_college.vo.OrgRecruitVO;
import training_college.vo.OrgStudyVO;

import java.util.List;

/**
 * Created by ss14 on 2017/3/18.
 */
public interface StatsService {

    //获取一个机构的全部预订记录
    List<Reservation> getReservationByOid(int oid);
    //获取一个机构的全部预订收入
    int getReserveSumByOid(int oid);

    //获取一个机构的全部退订记录
    List<Reservation> getUnreservationByOid(int oid);
    //获取一个机构的全部退订支出
    int getUnreserveSumByOid(int oid);

    //获取一个机构的全部入学记录
    List<EnrollmentRecord> getEnrollRecordByOid(int oid);
    //获取一个机构的全部入学收入
    int getEnrollSumByOid(int oid);

    //获取一个机构的全部退学记录
    List<DropRecord> getDropRecordByOid(int oid);
    //获取一个机构的全部退学支出
    int getDropSumByOid(int oid);


    List<Object [] > getCourseStatsByOid(int oid);

    OrgRecruitVO getOrgRecruitVOByOid(int oid);

    OrgStudyVO getOrgStudyVOByOid(int oid);

    OrgFinanceVO getOrgFinanceVOByOid(int oid);

    List<OrgRecruitVO> getAllOrgRecruitVO();

    List<OrgStudyVO> getAllOrgStudyVO();

    List<OrgFinanceVO> getAllOrgFinanceVO();





}
