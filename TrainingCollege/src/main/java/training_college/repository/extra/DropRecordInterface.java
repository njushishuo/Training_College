package training_college.repository.extra;

import java.util.List;

/**
 * Created by ss14 on 2017/3/18.
 */
public interface DropRecordInterface {

    List<String> getRepaymentUncheckedOrgSystemIds();


    int getRepaymentSumByOrgSystemId(String sysid);


    boolean settleRepaymentByOrgSysId(String sysId);

    int getDropSumByOid(String oid);

}
