package training_college.service;

import training_college.entity.Reservation;

import java.util.List;

/**
 * Created by ss14 on 2017/3/15.
 */
public interface ReserveService {

    /**
     * 会员预订只能使用会员卡
     * @param sid
     * @param pid
     * @return
     */
    boolean reserve(int sid , int pid);


    /**
     * 会员退订
     * @return
     */
    boolean unreserve(int reservationId);

    List<Reservation> getNotStartedReservationBySid(int sid);

    List<Reservation> getReservationBySid(int sid);

    List<Reservation> getUnReservationBySid(int sid);

}
