package training_college.service;

import java.util.List;

/**
 * Created by ss14 on 2017/3/17.
 */
public interface SettleService {


    /**
     * 只获取那些用过入学记录的机构SysID
     * @return
     */
    List<String> getOrgSystemIds();

    /**
     * 获取机构入学收费的总额
     * @return
     */
    int  getPaymentSumByOrgSysId(String sysId);


    /**
     * 获取机构的退课还款的总额
     * @return
     */
    int  getRepaymentSumByOrgId(int id);
}
