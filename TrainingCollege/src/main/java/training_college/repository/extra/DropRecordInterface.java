package training_college.repository.extra;

import java.util.List;

/**
 * Created by ss14 on 2017/3/18.
 */
public interface DropRecordInterface {

    int getRepaymentSumByOrgSystemId(String sysid);

    boolean settleRepaymentByOrgSysId(String sysId);

    int getDropSumByOid(String oid);

    int getDropCntByOid(String oid);

}
