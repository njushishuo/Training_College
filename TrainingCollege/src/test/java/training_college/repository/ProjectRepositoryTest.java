package training_college.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import training_college.entity.Project;


import java.util.List;


/**
 * Created by ss14 on 2017/3/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryTest {
    @Autowired
    ProjectRepository repository;

    @Test
    public void getOpenClasses() {

        List<Project> list = repository.getApprovedProjectsByOrgId(1);
        for(Project project : list){
            System.out.println(project.getId()+" "+project.getClassName()+" "+project.getFromDate()
            );
        }


    }
}