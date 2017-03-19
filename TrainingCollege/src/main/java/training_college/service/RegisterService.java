package training_college.service;

/**
 * Created by ss14 on 2017/3/19.
 */
public interface RegisterService {

    boolean stdRegister(String username ,String password);

    boolean orgRegister(String username , String password);

}
