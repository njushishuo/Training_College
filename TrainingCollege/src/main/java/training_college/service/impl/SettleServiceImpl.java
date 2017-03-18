package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training_college.entity.Company;
import training_college.repository.CompanyRepository;
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
    CompanyRepository companyRepository;
    @Autowired
    IDHelper idHelper;


    @Override
    public List<String> getPaymentUncheckedOrgSystemIds() {
        return enrollRecordRepository.getPaymentUncheckedOrgSystemIds();
    }

    @Override
    public List<String> getRepaymentUncheckedOrgSystemIds() {
        return dropRecordRepository.getRepaymentUncheckedOrgSystemIds();
    }

    @Override
    public int getPaymentSumByOrgSysId(String sysId) {

        return enrollRecordRepository.getPaymentSumByOrgSystemId(sysId);

    }


    @Override
    public int getRepaymentSumBySysOrgId(String sysId) {

        return dropRecordRepository.getRepaymentSumByOrgSystemId(sysId);

    }

    @Override
    @Transactional
    public boolean settlePaymentByOrgSysId(String sysId , int payment) {

        Company company = companyRepository.getOne(1);
        int balance = company.getBalance();
        company.setBalance(balance + payment );
        companyRepository.saveAndFlush(company);

        return enrollRecordRepository.settlePaymentByOrgSysId(sysId);
    }



    @Override
    public boolean settleRepaymentByOrgSysId(String sysId, int payment) {
        Company company = companyRepository.getOne(1);
        int balance = company.getBalance();
        company.setBalance(balance - payment );
        companyRepository.saveAndFlush(company);

        return dropRecordRepository.settleRepaymentByOrgSysId(sysId);
    }

    @Override
    public int getCompanyBalance() {
        Company company = companyRepository.findOne(1);
        return company.getBalance();
    }
}
