package training_college.repository.extra;

import java.util.List;

/**
 * Created by ss14 on 2017/3/12.
 */
public interface CourseInterface {

    /**
     * 根据班级ID返回，该班级的全部课程， 从 pre_modify_schedule中读取最新情况
     * @return
     */
    List getCoursesByProjectId(int id);
}
