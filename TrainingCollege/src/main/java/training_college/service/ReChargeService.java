package training_college.service;

import training_college.entity.Card;

/**
 * Created by ss14 on 2017/3/19.
 */
public interface ReChargeService {

    boolean reCharge(Card card , String num , String status);


}
