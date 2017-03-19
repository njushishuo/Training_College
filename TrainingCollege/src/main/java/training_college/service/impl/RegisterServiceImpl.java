package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training_college.entity.BankCard;
import training_college.entity.Card;
import training_college.entity.Organization;
import training_college.entity.Student;
import training_college.repository.BankCardRepository;
import training_college.repository.CardRepository;
import training_college.repository.OrganizationRepository;
import training_college.repository.StudentRepository;
import training_college.service.ReChargeService;
import training_college.service.RegisterService;
import training_college.util.enumeration.CardStatus;
import training_college.util.enumeration.Gender;

import java.util.Random;

/**
 * Created by ss14 on 2017/3/19.
 */

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    StudentRepository studentRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    BankCardRepository bankCardRepository;
    @Autowired
    CardRepository cardRepository;

    @Override
    public boolean stdRegister(String username, String password) {

        //S1: 创建新的银行卡
        BankCard bankCard = new BankCard();
        Random random = new Random();
        int num = random.nextInt(10000);
        String numString = num+"";
        while(numString.length()!=4){
            numString='0'+numString;
        }

        String cardNum = "存储卡(**** "+numString+")" ;
        bankCard.setCardNum(cardNum);
        bankCard.setBalance(10000);
        bankCard = bankCardRepository.saveAndFlush(bankCard);


        //S2: 创建新的会员卡
        Card card = new Card();
        card.setBankCardId(bankCard.getId());
        card.setStatus(CardStatus.newly);
        card.setScore(0);
        card.setLevel(1);
        card = cardRepository.saveAndFlush(card);


        //S3: 创建新的student
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(password);
        student.setCard(card);
        student.setGender(Gender.male);
        student = studentRepository.saveAndFlush(student);

        return true;
    }

    @Override
    public boolean orgRegister(String username, String password) {


        Organization organization = new Organization();
        organization.setUsername(username);
        organization.setPassword(password);
        organization.setBalance(10000);
        organization = organizationRepository.saveAndFlush(organization);

        return true;
    }

}
