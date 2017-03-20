package training_college.service;

import java.util.List;

/**
 * Created by ss14 on 2017/3/17.
 */
public interface SettleService {


    /**
     * 只获取那些收支仍未结算的机构SysID
     * 因为收入和支出是同时结算的所以只需获取收入没有结算的机构ID即可
     * @return
     */
    List<String> getPaymentUncheckedOrgSystemIds();

    /*
     * 获取机构入学收费的总额
     * @return
     */
    int  getPaymentSumByOrgSysId(String sysId);


    boolean settleByOrgSysId(String sysId,int profit);


    /**
     * 获取机构的退课还款的总额
     * @return
     */
    int  getRepaymentSumBySysOrgId(String id);


    /**
     * 获取公司账户余额
     * @return
     */
    int getCompanyBalance();
}
