package training_college.repository.extra;

import training_college.entity.Project;

import java.util.List;


/**
 * Created by ss14 on 2017/3/12.
 */
public interface ProjectInterface {



    /**
     * 返回某个机构的班级
     * 获取开课申请成功的班级
     * 如果正在审批修改，显示修改前的信息
     * @return
     */
    List<Project> getOpenProjectsByOrgId(int id);

    /**
     * 获取所有开放的班级
     * @return
     */
    List<Project> getAllOpenProjects();

    /**
     * 返回某个机构申请开班的记录
     * @param id
     * @return
     */
    List<Project> getAllProjectsByOrgId(int id);

    /**
     * 返回某个机构所有有过修改的班级
     * @param id
     * @return
     */
    List<Project> getAllModifiedProjectsByOrgId(int id);


    /**
     * 获取某个机构所有仍未满员的开放班级
     * @param id
     * @return
     */
    List<Project> getAvaliableProjectsByOrgId(int id);


    /**
     * 获取某个用户没有选过的所有仍未满员的开放班级
     * @param id
     * @return
     */
    List<Project> getAvaliableProjectsNotSelectedByStdId(int id);

    /**
     * 获取某个机构所有已选人数>0的开放班级
     * @param id
     * @return
     */
    List<Project> getSelectedProjectsByOrgId(int id);


}
