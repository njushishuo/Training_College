package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.PostProject;
import training_college.entity.Project;
import training_college.repository.PostProjectRepository;
import training_college.service.ProjectTransforService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ss14 on 2017/3/19.
 */
@Service
public class ProjectTranforServiceImpl implements ProjectTransforService {

    @Autowired
    PostProjectRepository postProjectRepository;

    @Override
    public List<Project> getPostProjectsByProjects(List<Project> projects) {


        List<Project> modifiedProjects = new LinkedList<>();
        for(Project project: projects){

            PostProject postProject = postProjectRepository.getOne(project.getId());
            project.setClassName(postProject.getClassName());
            project.setFromDate(postProject.getFromDate());
            project.setToDate(postProject.getToDate());
            project.setTotalPrice(postProject.getTotalPrice());
            modifiedProjects.add(project);
        }


        return modifiedProjects;
    }
}
