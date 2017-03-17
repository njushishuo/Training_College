package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.Company;

/**
 * Created by ss14 on 2017/3/17.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Company findByUsername(String username);

}
