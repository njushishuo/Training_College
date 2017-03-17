package training_college.service;

import training_college.entity.Project;

import java.util.List;

/**
 * Created by ss14 on 2017/3/17.
 */
public interface ExamService {


    /**
     * 获取等待审核的开班申请
     * @return
     */
    List<Project> getPendingNewProjects();


    /**
     * 获取所有处理过的开班申请
     * @return
     */
    List<Project> getAllProcessedNewProjects();

    /**
     * 获取等待审核的修改申请
     * @return
     */
    List<Project> getPendingModifyProjects();


    /**
     * 获取所有处理过的修改申请
     * @return
     */
    List<Project> getAllProcessedModifyProjects();


    boolean approveNewProject(int pid);

    boolean rejectNewProject(int pid);


    boolean approveModifyProject(int pid);

    boolean rejectModifyProject(int pid);


}
