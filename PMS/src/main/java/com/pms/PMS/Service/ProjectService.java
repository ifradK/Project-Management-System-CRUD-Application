package com.pms.PMS.Service;

import com.pms.PMS.Entity.ProjectDtls;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    List<ProjectDtls> getAllProjects();

    ProjectDtls saveProject(ProjectDtls projectDtls);

    ProjectDtls getProjectById(Long id);

    ProjectDtls updateProject(ProjectDtls projectDtls);

    void deleteProjectById(Long id);

    List<ProjectDtls> getProjectsInDateRange(Date startDate, Date endDate);
}