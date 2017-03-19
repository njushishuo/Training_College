package training_college.service;

import training_college.entity.Project;

import java.util.List;

/**
 * Created by ss14 on 2017/3/19.
 */
public interface ProjectTransforService {

    /**
     * 获取待修改审批的课程后，需要展示的课程的基本信息应该是修改后的
     * @param projects
     * @return
     */
    List<Project> getPostProjectsByProjects(List<Project> projects);

}
