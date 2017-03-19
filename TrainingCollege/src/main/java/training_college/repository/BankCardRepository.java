package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.BankCard;
import training_college.repository.extra.BankCardInterface;

/**
 * Created by ss14 on 2017/3/16.
 */
@Repository
public interface BankCardRepository extends JpaRepository<BankCard,Integer>,BankCardInterface {


    BankCard getByCardNum(String num);

}
