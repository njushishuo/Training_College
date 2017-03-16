package training_college.repository.extra;

import training_college.entity.Reservation;

import java.util.List;

/**
 * Created by ss14 on 2017/3/16.
 */
public interface ReservationInterface {


    List<Reservation> getNotStartedReservationBySid(int sid);


    List<Reservation> getUnReservationsBySid(int sid);
}
