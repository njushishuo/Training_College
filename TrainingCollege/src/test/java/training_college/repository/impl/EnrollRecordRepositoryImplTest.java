package training_college.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import training_college.repository.EnrollRecordRepository;

import static org.junit.Assert.*;

/**
 * Created by ss14 on 2017/3/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnrollRecordRepositoryImplTest {

    @Autowired
    EnrollRecordRepository enrollRecordRepository;

    @Test
    public void getEnrollRecordsWithSelectionByStdName() throws Exception {

    }

    @Test
    public void getPaymentSumByOrgSystemId() throws Exception {

        int target = 2700;
        String id = "0000001";

        assert target == enrollRecordRepository.getPaymentSumByOrgSystemId(id) ;
    }

    @Test
    public void getRepaymentSumByOrgSystemId() throws Exception {
        int target = 250;
        String id = "0000001";

        assert target == enrollRecordRepository.getRepaymentSumByOrgSystemId(id) ;
    }

}