package training_college.service.impl;

import training_college.entity.DropRecord;
import training_college.entity.EnrollmentRecord;
import training_college.entity.Reservation;
import training_college.service.ConsumptionService;

import java.util.List;

/**
 * Created by ss14 on 2017/3/16.
 */
public class ConsumptionServiceImpl implements ConsumptionService {
    @Override
    public int getSumByReservations(List<Reservation> reservations) {
        int sum=0;
        for(Reservation reservation :reservations){
            sum+=reservation.getPayment();
        }
        return sum;
    }

    @Override
    public int getSumByUnReservations(List<Reservation> unReservations) {

        int sum=0;
        for(Reservation unReservation : unReservations){
            sum+=unReservation.getPayment();
        }
        return sum;
    }

    @Override
    public int getSumByEnrollments(List<EnrollmentRecord> enrollmentRecords) {

        int sum=0;
        for(EnrollmentRecord enrollmentRecord : enrollmentRecords){
            sum+=enrollmentRecord.getPayment();
        }
        return sum;
    }

    @Override
    public int getSumByDropRecords(List<DropRecord> dropRecords) {
        int sum=0;
        for (DropRecord dropRecord :dropRecords){
            sum+= dropRecord.getPayment();
        }
        return sum;
    }
}
