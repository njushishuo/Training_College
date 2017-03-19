package training_college.repository.extra;

import training_college.entity.Card;

import java.util.List;

/**
 * Created by ss14 on 2017/3/19.
 */
public interface CardInterface {

    List<Card> getToFrozenCards();

    List<Card> getToDisableCards();

}
