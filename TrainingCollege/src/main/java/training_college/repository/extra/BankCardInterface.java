package training_college.repository.extra;

import training_college.entity.BankCard;

import java.util.List;

/**
 * Created by ss14 on 2017/3/16.
 */
public interface BankCardInterface  {

    List<BankCard> getByStudentId(int id);

}
