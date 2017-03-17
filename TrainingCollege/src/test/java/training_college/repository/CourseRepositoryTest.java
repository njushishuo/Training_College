package training_college.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import training_college.entity.Course;
import training_college.entity.Teacher;


import java.util.List;


/**
 * Created by ss14 on 2017/3/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    CourseRepository repository;

    @Test
    public void getCoursesByProjectId(){


        List<Course> list = repository.getPreModifyCoursesByProjectId(1);
        for(Course course : list){
            System.out.print(course.getName()+" "+course.getDescription()+" ");
            for(Teacher teacher : course.getTeacherList() ){
                System.out.println(teacher.getName());
            }
        }


    }
}