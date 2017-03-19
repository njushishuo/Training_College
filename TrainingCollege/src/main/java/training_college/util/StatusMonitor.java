package training_college.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import training_college.repository.CardRepository;
import training_college.entity.Card;
import training_college.util.enumeration.CardStatus;

import java.util.List;


/**
 * Created by ss14 on 2017/3/19.
 */

@Component
public class StatusMonitor {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    DateHelper dateHelper;

    final  int daysPerYear = 365;
    final  int executeRate = 1000*60*60*24;



    @Scheduled(fixedRate = 2000)
    public void FrozenCards(){

        List<Card> toFrozenCards = cardRepository.getToFrozenCards();
        for(Card card :toFrozenCards){

            int diffInDays = dateHelper.getDiffOfDays(card.getLastActivatedAt());
            if(diffInDays >= daysPerYear ){
                card.setStatus(CardStatus.frozen);
                cardRepository.saveAndFlush(card);
            }
        }
    }


    @Scheduled(fixedRate = 2000)
    public void DisableCards(){

        List<Card> toDisableCards = cardRepository.getToDisableCards();
        for(Card card :toDisableCards){

            int diffInDays = dateHelper.getDiffOfDays(card.getLastActivatedAt());
            if(diffInDays >= 2* daysPerYear ){
                card.setStatus(CardStatus.disabled);
                cardRepository.saveAndFlush(card);
            }
        }
    }


}
