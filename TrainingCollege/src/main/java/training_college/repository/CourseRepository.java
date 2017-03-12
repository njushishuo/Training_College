package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.Course;
import training_college.repository.extra.CourseInterface;

/**
 * Created by ss14 on 2017/3/12.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> , CourseInterface{

}
