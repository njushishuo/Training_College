package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.DropRecord;
import training_college.entity.EnrollmentRecord;
import training_college.entity.Organization;
import training_college.entity.Reservation;
import training_college.repository.*;
import training_college.service.StatsService;
import training_college.util.IDHelper;
import training_college.vo.CourseVO;
import training_college.vo.OrgRecruitVO;
import training_college.vo.OrgStudyVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ss14 on 2017/3/18.
 */
@Service
public class StatsServiceImpl implements StatsService {


    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EnrollRecordRepository enrollRecordRepository;
    @Autowired
    DropRecordRepository dropRecordRepository;
    @Autowired
    GradeRecordRepository gradeRecordRepository;
    @Autowired
    OrganizationRepository organizationRepository;



    @Autowired
    IDHelper idHelper;


    @Override
    public List<Reservation> getReservationByOid(int oid) {
        return reservationRepository.getReservationByOid(oid);
    }

    @Override
    public int getReserveSumByOid(int oid) {
        return reservationRepository.getReserveSumByOid(oid);
    }

    @Override
    public List<Reservation> getUnreservationByOid(int oid) {
        return reservationRepository.getUnreservationByOid(oid);
    }

    @Override
    public int getUnreserveSumByOid(int oid) {
        return reservationRepository.getUnreserveSumByOid(oid);
    }

    @Override
    public List<EnrollmentRecord> getEnrollRecordByOid(int oid) {

        String sysId = idHelper.validateId(oid);
        return enrollRecordRepository.getByOrgSystemIdOrderByDateDesc(sysId);

    }

    @Override
    public int getEnrollSumByOid(int oid) {

        String id = idHelper.validateId(oid);
        return enrollRecordRepository.getEnrollSumByOid(id);
    }

    @Override
    public List<DropRecord> getDropRecordByOid(int oid) {

        String sysId = idHelper.validateId(oid);
        return dropRecordRepository.getByOrgSystemIdOrderByDateDesc(sysId);

    }

    @Override
    public int getDropSumByOid(int oid) {

        String id = idHelper.validateId(oid);
        return dropRecordRepository.getDropSumByOid(id);
    }

    @Override
    public List<Object[]> getCourseStatsByOid(int oid) {

        String sysId = idHelper.validateId(oid);
        return gradeRecordRepository.getCourseStatsByOrgSysId(sysId);
    }

    @Override
    public OrgRecruitVO getOrgRecruitVOByOid(int oid) {
        String id =idHelper.validateId(oid);
        Organization organization = organizationRepository.findOne(oid);
        OrgRecruitVO vo = new OrgRecruitVO() ;
        vo.orgId = id;
        vo.reserveCnt = reservationRepository.getReserveCntByOid(oid);
        vo.unReserveCnt = reservationRepository.getUnreserveCntByOid(oid);
        vo.enrollCnt = enrollRecordRepository.getEnrollCntByOid(id);
        vo.dropCnt = dropRecordRepository.getDropCntByOid(id);
        vo.curStdCnt = vo.enrollCnt - vo.dropCnt;

        return vo;
    }

    @Override
    public OrgStudyVO getOrgStudyVOBuyOid(int oid) {
        String sysId = idHelper.validateId(oid);
        OrgStudyVO orgStudyVO = new OrgStudyVO();
        orgStudyVO.orgId =sysId;
        orgStudyVO.projectCnt = organizationRepository.getProjectCntByOrgId(oid);
        orgStudyVO.courseCnt = organizationRepository.getCourseCntByOrgId(oid);
        orgStudyVO.stdCnt =  organizationRepository.getCurStdCntByOrgId(oid);

        //Object []  => CourseVO
        List<Object []> courseStasList = gradeRecordRepository.getCourseStatsByOrgSysId(sysId);
        List<CourseVO> courseVOs = new LinkedList<>();
        for(int i=0;i<courseStasList.size();i++){
            CourseVO courseVO = new CourseVO();
            courseVO.orgId = sysId;
            courseVO.courseName = (String) courseStasList.get(i)[0];
            courseVO.min = (int) courseStasList.get(i)[1];
            courseVO.max = (int) courseStasList.get(i)[2];
            courseVO.avg = (int)(double) courseStasList.get(i)[3];
            courseVOs.add(courseVO);
        }

        orgStudyVO.courseVOs = courseVOs;
        return orgStudyVO;
    }


    @Override
    public List<OrgRecruitVO> getAllOrgRecruitVO() {

        List<Integer> orgIds = organizationRepository.getAllOrgId();
        List<OrgRecruitVO> orgRecruitVOs = new LinkedList<>();
        for(int i=0;i<orgIds.size();i++){
            OrgRecruitVO vo = getOrgRecruitVOByOid(orgIds.get(i));
            orgRecruitVOs.add(vo);
        }

        return orgRecruitVOs;
    }

    @Override
    public List<OrgStudyVO> getAllOrgStudyVO() {
        List<Integer> orgIds = organizationRepository.getAllOrgId();
        List<OrgStudyVO> orgStudyVOs = new LinkedList<>();
        for(int i=0;i<orgIds.size();i++){
            OrgStudyVO vo = getOrgStudyVOBuyOid(orgIds.get(i));
            orgStudyVOs.add(vo);
        }

        return orgStudyVOs;
    }
}
