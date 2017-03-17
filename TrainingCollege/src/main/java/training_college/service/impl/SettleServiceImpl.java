package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.repository.DropRecordRepository;
import training_college.repository.EnrollRecordRepository;
import training_college.service.SettleService;
import training_college.util.IDHelper;

import java.util.List;

/**
 * Created by ss14 on 2017/3/17.
 */
@Service
public class SettleServiceImpl implements SettleService {

    @Autowired
    EnrollRecordRepository enrollRecordRepository;
    @Autowired
    DropRecordRepository dropRecordRepository;
    @Autowired
    IDHelper idHelper;


    @Override
    public List<String> getOrgSystemIds() {
        return enrollRecordRepository.getOrgSystemIds();
    }

    @Override
    public int getPaymentSumByOrgSysId(String sysId) {

        return enrollRecordRepository.getPaymentSumByOrgSystemId(sysId);

    }

    @Override
    public int getRepaymentSumByOrgId(int id) {

        String sysId = idHelper.validateId(id);
        return enrollRecordRepository.getRepaymentSumByOrgSystemId(sysId);
    }
}
