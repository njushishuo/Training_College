package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.StudentBankCard;
import training_college.entity.StudentBankCardPK;

/**
 * Created by ss14 on 2017/3/16.
 */
@Repository
public interface StudentBankCardRepository extends JpaRepository<StudentBankCard,StudentBankCardPK> {
}
