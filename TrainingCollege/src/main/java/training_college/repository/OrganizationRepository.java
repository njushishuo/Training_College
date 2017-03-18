package training_college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_college.entity.Organization;
import training_college.repository.extra.OrganizationInterface;


/**
 * Created by ss14 on 2017/3/12.
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer>,OrganizationInterface {

    Organization findByUsername(String username);
}
