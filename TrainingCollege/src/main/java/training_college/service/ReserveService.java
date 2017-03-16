package training_college.service;

import training_college.entity.Reservation;

import java.util.List;

/**
 * Created by ss14 on 2017/3/15.
 */
public interface ReserveService {

    /**
     * 会员预订
     * @param sid
     * @param pid
     * @return
     */
    boolean reserve(int sid , int pid);

    boolean unreserve(int reservationId);

    List<Reservation> getNotStartedReservationBySid(int sid);

    List<Reservation> getReservationBySid(int sid);

    List<Reservation> getUnReservationBySid(int sid);

}
