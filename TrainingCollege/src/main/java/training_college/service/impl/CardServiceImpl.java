package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.BankCard;
import training_college.entity.Card;
import training_college.entity.Student;
import training_college.repository.BankCardRepository;
import training_college.repository.CardRepository;
import training_college.repository.StudentRepository;
import training_college.service.CardService;
import training_college.util.enumeration.CardStatus;

import java.util.List;

/**
 * Created by ss14 on 2017/3/15.
 */

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    StudentRepository studentRepository ;
    @Autowired
    BankCardRepository bankCardRepository;
    @Autowired
    CardRepository cardRepository;


    @Override
    public Student getStudentById(int id) {
        return studentRepository.getOne(id);
    }

    @Override
    public void saveStudent(Student student) {
         studentRepository.saveAndFlush(student);
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.saveAndFlush(card);
    }

    @Override
    public List<BankCard> getBankCardsBySid(int id) {
        return bankCardRepository.getByStudentId(id);
    }

    @Override
    public BankCard getBankCardById(int id) {
        return  bankCardRepository.findOne(id);
    }

    @Override
    public Student stopMembership(Student student) {
        Card card =student.getCard();
        card.setStatus(CardStatus.disabled);
        card = cardRepository.saveAndFlush(card);
        student.setCard(card);
        return  studentRepository.saveAndFlush(student);
    }
}
