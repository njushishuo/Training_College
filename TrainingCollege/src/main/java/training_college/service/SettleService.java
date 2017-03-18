package training_college.service;

import java.util.List;

/**
 * Created by ss14 on 2017/3/17.
 */
public interface SettleService {


    /**
     * 只获取那些入学收入仍未结算的机构SysID
     * @return
     */
    List<String> getPaymentUncheckedOrgSystemIds();

    /**
     * 只获取那些退课支出仍未结算的机构SysID
     * @return
     */
    List<String> getRepaymentUncheckedOrgSystemIds();


    /**
     * 获取机构入学收费的总额
     * @return
     */
    int  getPaymentSumByOrgSysId(String sysId);


    boolean settlePaymentByOrgSysId(String sysId,int payment);


    /**
     * 获取机构的退课还款的总额
     * @return
     */
    int  getRepaymentSumBySysOrgId(String id);

    boolean settleRepaymentByOrgSysId(String sysId,int payment);



    /**
     * 获取公司账户余额
     * @return
     */
    int getCompanyBalance();
}
