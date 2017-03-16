package training_college.service;

import training_college.entity.DropRecord;
import training_college.entity.EnrollmentRecord;
import training_college.entity.Reservation;

import java.util.List;

/**
 * Created by ss14 on 2017/3/16.
 */
public interface ConsumptionService {

    int getSumByReservations(List<Reservation> reservations);

    int getSumByUnReservations(List<Reservation> unReservations);

    int getSumByEnrollments(List<EnrollmentRecord> enrollmentRecords);

    int getSumByDropRecords(List<DropRecord> dropRecords);

}
