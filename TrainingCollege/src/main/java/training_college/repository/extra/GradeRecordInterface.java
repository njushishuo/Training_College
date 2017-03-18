package training_college.repository.extra;

import java.util.List;

/**
 * Created by ss14 on 2017/3/18.
 */
public interface GradeRecordInterface {


    /**
     * 获取一个机构的所有课程的选课人数，最高分，最低分，平均分
     * @return
     */
    List<Object []> getCourseStatsByOrgSysId(String oid);

}
