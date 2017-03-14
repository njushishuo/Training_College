package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.PostProject;

/**
 * Created by ss14 on 2017/3/14.
 */

@Repository
public interface PostProjectRepository extends JpaRepository<PostProject, Integer> {
}
