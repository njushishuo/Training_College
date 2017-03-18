package training_college.repository.extra;

import training_college.entity.EnrollmentRecord;

import java.util.List;

/**
 * Created by ss14 on 2017/3/16.
 */
public interface EnrollRecordInterface {

    List<EnrollmentRecord> getEnrollRecordsWithSelectionByStdName(String name);

    int getPaymentSumByOrgSystemId(String  systemid);

    boolean settlePaymentByOrgSysId(String sysId);

    List<String> getPaymentUncheckedOrgSystemIds();

    int getEnrollSumByOid(String oid);

}

