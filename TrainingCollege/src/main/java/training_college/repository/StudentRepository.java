package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.Student;

/**
 * Created by ss14 on 2017/3/12.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByUsername(String username);

    Student findByName(String sname);

}
