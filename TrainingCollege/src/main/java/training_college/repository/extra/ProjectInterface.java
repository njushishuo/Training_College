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
    List<Project> getOpenClassesByOrgId(int id);

}
