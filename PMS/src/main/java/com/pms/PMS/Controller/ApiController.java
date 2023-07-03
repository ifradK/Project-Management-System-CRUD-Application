package com.pms.PMS.Controller;

import com.pms.PMS.Entity.ProjectDtls;
import com.pms.PMS.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
public class ApiController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/GET/api/v1/projects")
    public List<ProjectDtls> getProjects()
    {
        return projectService.getAllProjects();
    }
}
