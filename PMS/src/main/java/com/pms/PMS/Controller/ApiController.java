package com.pms.PMS.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.PMS.Entity.ProjectDtls;
import com.pms.PMS.Service.ProjectService;
import com.pms.PMS.Service.UserService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@Controller
public class ApiController {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/GET/api/v1/projects", produces = "application/json")
    public List<ProjectDtls> getProjects()
    {
        return projectService.getAllProjects();
    }

    @GetMapping("/GET/api/users")
    public List<String> getUserNames()
    {
        return userService.getAllUsernames();
    }
}



//    @GetMapping("/GET/api/users")
//    public ModelAndView getUserNames()
//    {
//        ObjectMapper mapper = new ObjectMapper();
//
//        List<String> list = new ArrayList<String>();
//        list.add("List A");
//        list.add("List B");
//        list.add("List C");
//        list.add("List D");
//        list.add("List E");
//
//        ModelAndView model = new ModelAndView("somepage");
//
//        String json = "";
//        try {
//            json = mapper.writeValueAsString(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        model.addObject("list", json);
//
//        return model;
////        return userService.getAllUsernames();
//    }