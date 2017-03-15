package training_college.service;

/**
 * Created by ss14 on 2017/3/15.
 */
public interface ReserveService {

    /**
     * 会员预订
     * @param sid
     * @param pid
     * @return
     */
    boolean reserve(int sid , int pid);

}
