package training_college.util;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ss14 on 2017/3/15.
 */
@Component
public class DateHelper {

    public boolean HasStarted(Date fromDate){
        java.util.Date today =new java.util.Date();
        return today.after(fromDate);
    }


    public int getDiffOfDays(Date lastActivatedAt){
        java.util.Date today =new java.util.Date();
        java.util.Date last = lastActivatedAt;

        long diff = today.getTime() - last.getTime();

        return (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }


    public Date getToday(){
        java.util.Date date = new java.util.Date();
        Date today = new Date(date.getTime());
        return today;
    }
}
