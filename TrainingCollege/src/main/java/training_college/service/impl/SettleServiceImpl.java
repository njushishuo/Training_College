package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training_college.entity.Company;
import training_college.entity.Organization;
import training_college.repository.CompanyRepository;
import training_college.repository.DropRecordRepository;
import training_college.repository.EnrollRecordRepository;
import training_college.repository.OrganizationRepository;
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
    OrganizationRepository organizationRepository;


    @Autowired
    IDHelper idHelper;


    @Override
    public List<String> getPaymentUncheckedOrgSystemIds() {
        return enrollRecordRepository.getPaymentUncheckedOrgSystemIds();
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
    public boolean settleByOrgSysId(String sysId , int profit) {

        Company company = companyRepository.getOne(1);
        int cpBalance = company.getBalance();
        if(cpBalance < profit){
            return false;
        }
        company.setBalance(cpBalance - profit);
        companyRepository.saveAndFlush(company);

        int orgId = Integer.parseInt(sysId);
        Organization organization = organizationRepository.findOne(orgId);
        int orgBalance = organization.getBalance();
        organization.setBalance(orgBalance+profit);
        organizationRepository.saveAndFlush(organization);

        //修改该机构的入学和退学记录，标记为checked
        enrollRecordRepository.settlePaymentByOrgSysId(sysId);
        dropRecordRepository.settleRepaymentByOrgSysId(sysId);

        return true;
    }


    @Override
    public int getCompanyBalance() {
        Company company = companyRepository.findOne(1);
        return company.getBalance();
    }
}
