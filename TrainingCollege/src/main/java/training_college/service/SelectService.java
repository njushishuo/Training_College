package training_college.service;

/**
 * Created by ss14 on 2017/3/15.
 */
public interface SelectService {


    /**
     * 会员线上选课
     * @param sid
     * @param pid
     * @return
     */
    boolean select(int sid, int pid);


    /**
     * 会员线下选课
     * @param sid
     * @param pid
     * @return
     */
    boolean selectOffline(int sid, int pid , int payment);


    /**
     * 会员线上退课
     * @param sid
     * @param pid
     * @return
     */
    boolean unselect(int sid , int pid);


    /**
     * 会员线下退课
     * @param sid
     * @param pid
     * @return
     */
    boolean unselectOffline(int sid, int pid , int repayment);



    int getSidBySname(String Sname);
}
