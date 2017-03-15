package training_college.util;

import org.springframework.stereotype.Component;

/**
 * Created by ss14 on 2017/3/15.
 */
@Component
public class IDHelper {

    /**
     * 将数据库ID转化为系统的7位ID
     * @param inputId
     * @return
     */
    public String validateId(int inputId){

        String id = String.valueOf(inputId);
        while(id.length()<7){
            id= '0'+id;
        }
        return id;
    }
}
