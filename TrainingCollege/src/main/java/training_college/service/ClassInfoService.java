package training_college.service;

import training_college.entity.Course;
import training_college.entity.Project;
import training_college.vo.ClassInfoVO;
import training_college.vo.ProjectVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ss14 on 2017/3/13.
 * 班级信息相关的逻辑
 */
public interface ClassInfoService {



    int getPriceByPid(int pid);

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

    /**
     * 获取一个会员选过的已经开学的课程
     * @param id
     * @return
     */
    List<Project> getStartedProjectsByStdId(int id);


    HashMap getPostModifyCourseMapByProejcts(List<Project> projects);

    HashMap getPreModifyCourseMapByProejcts(List<Project> projects);


    HashMap getNewCourseMapByProejcts(List<Project> projects);

    ClassInfoVO getClassInfoVOByProjects(List<Project> projects);


    List<Course> getPostModifyCoursesByProjectId(int id);

    List<Course> getPreModifyCoursesByProjectId(int id);

    List<Course> getNewCoursesByProjectId(int id);
}
