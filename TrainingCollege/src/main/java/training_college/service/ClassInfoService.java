package training_college.service;

import training_college.entity.Course;
import training_college.entity.Project;

import java.util.List;

/**
 * Created by ss14 on 2017/3/13.
 * 班级信息相关的逻辑
 */
public interface ClassInfoService {

    /**
     * 返回某个机构的班级
     * 获取开课申请成功的班级
     * 如果正在审批修改，显示修改前的信息
     * @return
     */
    List<Project> getOpenClassesByOrgId(int id);


    /**
     * 返回所有开放的班级
     * @return
     */
    List<Project> getAllOpenProjects();

    /**
     * 获取所有该用户未选过的仍未满员的开放课程
     * @param id
     * @return
     */
    List<Project> getAvaliableProjectsNotSelectedByStdId(int id);

    List<Course> getCoursesByProjectId(int id);
}
