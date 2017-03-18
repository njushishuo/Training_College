package training_college.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import training_college.repository.GradeRecordRepository;
import training_college.repository.extra.GradeRecordInterface;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ss14 on 2017/3/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeRecordRepositoryImplTest {
    @Autowired
    GradeRecordRepository gradeRecordInterface;


    @Test
    public void getCourseStatsByOrgSysId() throws Exception {

        List<Object[]> courseStats = gradeRecordInterface.getCourseStatsByOrgSysId("0000001");
        for(int i=0; i<courseStats.size();i++){

            for(int j=0;j<courseStats.get(i).length;j++ ){
                System.out.print( courseStats.get(i)[j]+" " );
            }

            System.out.print('\n');
        }

    }

}