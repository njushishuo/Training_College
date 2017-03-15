package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.ProjectStudent;
import training_college.entity.ProjectStudentPK;

/**
 * Created by ss14 on 2017/3/15.
 */
@Repository
public interface ProjectStudentRepository extends JpaRepository<ProjectStudent,ProjectStudentPK> {
}
