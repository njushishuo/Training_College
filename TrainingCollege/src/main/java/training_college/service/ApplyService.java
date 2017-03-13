package training_college.service;

import training_college.entity.Course;
import training_college.entity.Project;

import java.util.List;

/**
 * Created by ss14 on 2017/3/13.
 */
public interface ApplyService {


    Project saveAndFlush(Project project);

    /**
     * 返回某个机构申请开班的记录
     * @param id
     * @return
     */
    List<Project> getAllNewClassesByOrgId(int id);

    /**
     * 返回全部课程
     * @return
     */
    List<Course> getAllCourses();


    void addNewSchedule(String [] courseIds , int projectId );

}
