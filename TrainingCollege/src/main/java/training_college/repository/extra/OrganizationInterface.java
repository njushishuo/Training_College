package training_college.repository.extra;

import java.util.List;

/**
 * Created by ss14 on 2017/3/18.
 */
public interface OrganizationInterface {


    List<Integer> getAllOrgId();


    int getProjectCntByOrgId(int id);

    int getCourseCntByOrgId(int id);

    int getCurStdCntByOrgId(int id);

}
