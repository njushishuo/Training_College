package training_college.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import training_college.entity.Reservation;
import training_college.repository.ReservationRepository;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ss14 on 2017/3/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryImplTest {
    @Autowired
    ReservationRepository reservationRepository;


    @Test
    public void getNotStartedReservationBySid() throws Exception {

    }

    @Test
    public void getUnReservationsBySid() throws Exception
    {
        List<Reservation> reservationList = reservationRepository.getUnReservationsBySid(1);

        for(Reservation reservation: reservationList){
            System.out.println(reservation.getProject().getClassName());
        }

    }

}