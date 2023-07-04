package com.pms.PMS.Service.impl;

import com.pms.PMS.Entity.ProjectDtls;
import com.pms.PMS.Repository.ProjectRepository;
import com.pms.PMS.Service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        super();
        this.projectRepository = projectRepository;
    }


    @Override
    public List<ProjectDtls> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectDtls saveProject(ProjectDtls projectDtls) {
        return projectRepository.save(projectDtls);
    }

    @Override
    public ProjectDtls getProjectById(Long id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public ProjectDtls updateProject(ProjectDtls projectDtls) {
        return projectRepository.save(projectDtls);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectDtls> getProjectsInDateRange(Date startDate, Date endDate) {
        return projectRepository.findByStartGreaterThanEqualAndEndLessThanEqual(startDate, endDate);
    }

}