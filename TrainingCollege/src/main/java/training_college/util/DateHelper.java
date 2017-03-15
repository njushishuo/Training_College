package training_college.util;

import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Created by ss14 on 2017/3/15.
 */
@Component
public class DateHelper {

    public boolean HasStarted(Date fromDate){
        java.util.Date today =new java.util.Date();
        return today.after(fromDate);
    }
}
