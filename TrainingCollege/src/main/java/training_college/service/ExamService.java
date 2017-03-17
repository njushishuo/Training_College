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


    boolean approveNewProject(int pid);

    boolean rejectNewProject(int pid);


}
