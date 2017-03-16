package training_college.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import training_college.entity.Project;
import training_college.entity.Reservation;
import training_college.repository.extra.ReservationInterface;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ss14 on 2017/3/16.
 */
@Transactional
public class ReservationRepositoryImpl implements ReservationInterface {

    @PersistenceContext
    EntityManager em;


    @Override
    public List<Reservation> getNotStartedReservationBySid(int sid) {
        String hql = " from Reservation r  where r.student.id = ?1 and r.project.fromDate > CURRENT_DATE ";
        Query query   = em.createQuery(hql).setParameter(1,sid);
        List<Reservation> reservations = query.getResultList();
        return reservations;
    }

    @Override
    public List<Reservation> getUnReservationsBySid(int sid) {
        String hql = " from Reservation r  where r.student.id = ?1 and r.canceled is true ";
        Query query   = em.createQuery(hql).setParameter(1,sid);
        List<Reservation> reservations = query.getResultList();
        return reservations;
    }
}
