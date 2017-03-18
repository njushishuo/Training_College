package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.DropRecord;
import training_college.entity.EnrollmentRecord;
import training_college.entity.Reservation;
import training_college.repository.DropRecordRepository;
import training_college.repository.EnrollRecordRepository;
import training_college.repository.GradeRecordRepository;
import training_college.repository.ReservationRepository;
import training_college.service.StatsService;
import training_college.util.IDHelper;

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
}
