package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training_college.entity.BankCard;
import training_college.entity.Card;
import training_college.repository.BankCardRepository;
import training_college.repository.CardRepository;
import training_college.service.ReChargeService;
import training_college.util.DateHelper;
import training_college.util.enumeration.CardStatus;



/**
 * Created by ss14 on 2017/3/19.
 */
@Service
public class ReChargeServiceImpl implements ReChargeService {

    @Autowired
    DateHelper dateHelper;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    BankCardRepository bankCardRepository;

    @Override
    @Transactional
    public boolean reCharge(Card card , String num, String status) {
        int reChargeNum;
        if(num.length()>0){
            reChargeNum  = Integer.parseInt(num);
        }else{
            reChargeNum =0;
        }

        System.err.println("card:"+card);
        System.err.println("status:"+status);
        System.err.println("num:"+reChargeNum);


        BankCard bankCard = bankCardRepository.getOne(card.getBankCardId());
        bankCard.setBalance(bankCard.getBalance() - reChargeNum );
        bankCardRepository.saveAndFlush(bankCard);


        int cardBalance = card.getBalance();
        System.err.println("precardBalance:"+cardBalance);
        card.setBalance(cardBalance+reChargeNum);
        System.err.println("postcardBalance:"+card.getBalance());


        if(card.getBalance() >= 1000){
            card.setStatus(CardStatus.activated);
            card.setLastActivatedAt(dateHelper.getToday());
            cardRepository.saveAndFlush(card);
            return true;
        }else{
            cardRepository.saveAndFlush(card);
            return false;
        }
    }
}
