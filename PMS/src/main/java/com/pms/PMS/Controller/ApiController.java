package com.pms.PMS.Controller;

import com.pms.PMS.Entity.ProjectDtls;
import com.pms.PMS.Service.ProjectService;
import com.pms.PMS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Controller
public class ApiController {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @GetMapping("/GET/api/v1/projects")
    public List<ProjectDtls> getProjects()
    {
        return projectService.getAllProjects();
    }

    @GetMapping("/GET/api/users")
//    @RequestMapping
//    public List<String> getUserNames(@RequestParam(value = "term", required = false,
//            defaultValue = "") String term)
    public List<String> getUserNames()
    {
        return userService.getAllUsernames();
    }
}
