package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.Card;

/**
 * Created by ss14 on 2017/3/16.
 */

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
