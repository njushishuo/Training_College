package training_college.service;

import training_college.entity.Course;
import training_college.entity.PostProject;
import training_college.entity.Project;

import java.util.List;

/**
 * Created by ss14 on 2017/3/13.
 */
public interface ApplyService {


    Project getProjectById(int pid);

    /**
     * 返回某个机构申请开班的记录
     * @param id
     * @return
     */
    List<Project> getAllProjectsByOrgId(int id);



    /**
     * 返回某个机构申请修改的记录
     * @param id
     * @return
     */
    List<Project> getAllModifiedProjectsByOrgId(int id);

    /**
     * 返回全部课程
     * @return
     */
    List<Course> getAllCourses();

    /**
     * 开班申请
     * @param courseIds
     * @param projectId
     */
    void addNewSchedule(String [] courseIds , int projectId );


    void addPostProject(PostProject postProject);


    Project addProject(Project project);

    /**
     * 修改申请
     * @param courseIds
     * @param projectId
     */
    void addPostSchedule(String [] courseIds , int projectId );

}
