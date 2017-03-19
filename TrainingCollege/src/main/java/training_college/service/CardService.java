package training_college.service;

import training_college.entity.BankCard;
import training_college.entity.Card;
import training_college.entity.Student;

import java.util.List;

/**
 * Created by ss14 on 2017/3/15.
 */
public interface CardService {

    Student getStudentById(int id);

    void saveStudent(Student student);

    void saveCard(Card card);

    List<BankCard> getBankCardsBySid(int id);

    BankCard getBankCardById(int id);


}
