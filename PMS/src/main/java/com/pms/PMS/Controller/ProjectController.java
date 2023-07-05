package com.pms.PMS.Controller;

import com.pms.PMS.Entity.DateConv;
import com.pms.PMS.Entity.ProjectDtls;
import com.pms.PMS.Entity.UserDtls;
import com.pms.PMS.Service.ProjectService;
import com.pms.PMS.Service.ReportService;
import com.pms.PMS.Service.UserService;
import com.pms.PMS.Service.UtilityService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Calendar;
//import java.util.Date;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;


@Controller
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private UtilityService utilityService;


    public ProjectController(ProjectService projectService) {
        super();
        this.projectService = projectService;
    }



    @GetMapping("/projects/new")
    public String createProjectForm(Model model)
    {
        ProjectDtls projectDtls = new ProjectDtls();
        model.addAttribute("project", projectDtls);
        return "create_project";
    }



    @GetMapping("/projects")
    public String listProjects(Model model)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        java.sql.Date startOfMonth = new java.sql.Date(cal.getTime().getTime());

        int lastDateOfMonth = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDateOfMonth);
        java.sql.Date endOfMonth = new java.sql.Date(cal.getTime().getTime());

        model.addAttribute("projects", projectService.getProjectsInDateRange(startOfMonth, endOfMonth));
        return "projects";
    }



    @PostMapping("/projects")
    public String saveProject(@ModelAttribute("project") ProjectDtls projectDtls, HttpSession session)
    {
        try{
            if(projectDtls.getName()=="" || projectDtls.getStart()==null || projectDtls.getEnd()==null)
            {
                session.setAttribute("failuremessage", "Project Could not be Added. The Required* fields cannot be empty...");
                return "redirect:/projects/new";
            }
            else
            {
                String username = utilityService.getUsernameFromSession();

                projectDtls.setNumberOfProjectMembers(0);
                projectDtls.setOwner_name(username);
                projectService.saveProject(projectDtls);
                session.setAttribute("successmessage", "Project Added Successfully...");
                return "redirect:/projects";
            }
        }catch (Exception e)
        {
            session.setAttribute("failuremessage", "Error Occurred...");
            return "redirect:/projects";
        }
    }



    @PostMapping("/projects/inDateRange")
    public String listProjectsInDateRange(@ModelAttribute ProjectDtls projectDtls, Model model)
    {
        Date startDate = projectDtls.getStart();
        Date endDate = projectDtls.getEnd();
        model.addAttribute("projects", projectService.getProjectsInDateRange(startDate, endDate));
        return "projects";
    }



    @GetMapping("/projects/projectView/{id}")
    public String viewProject(@PathVariable Long id, Model model)
    {
        model.addAttribute("project", projectService.getProjectById(id));
        return "view_project";
    }



    @GetMapping("/projects/edit/{id}")
    public String editProjectForm(@PathVariable Long id, Model model, HttpSession session)
    {
        String username = utilityService.getUsernameFromSession();
        ProjectDtls projectDtls = projectService.getProjectById(id);

        if(username.equals(projectDtls.getOwner_name()))
        {
            model.addAttribute("project", projectDtls);
            return "edit_project";
        }
        else
        {
            session.setAttribute("failuremessage", "User is not the Owner. Only Owner can edit a project...");
            System.out.println("User is not the Owner. Only Owner can edit a project.");
            return "redirect:/projects";
        }
    }



    @PostMapping("/projects/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute("project") ProjectDtls projectDtls,
                                Model model, HttpSession session)
    {
        ProjectDtls existingProject = projectService.getProjectById(id);
        String username = utilityService.getUsernameFromSession();

        if(username.equals(existingProject.getOwner_name()))
        {
            try{
                if(projectDtls.getName()=="" || projectDtls.getStart()==null || projectDtls.getEnd()==null)
                {
                    session.setAttribute("failuremessage", "Project Could not be Updated. The Required* fields cannot be empty...");
                    return "redirect:/projects";
                }
                else
                {
                    existingProject.setName(projectDtls.getName());
                    existingProject.setIntro(projectDtls.getIntro());
                    existingProject.setStatus(projectDtls.getStatus());
                    existingProject.setStart(projectDtls.getStart());
                    existingProject.setEnd(projectDtls.getEnd());
                    existingProject.setNumberOfProjectMembers(projectDtls.getNumberOfProjectMembers());

                    projectService.updateProject(existingProject);
                    session.setAttribute("successmessage", "Project Updated Successfully...");
                    return "redirect:/projects";
                }
            }catch (Exception e)
            {
                session.setAttribute("failuremessage", "Error Occurred...");
                return "redirect:/projects";
            }
        }
        else
        {
            session.setAttribute("failuremessage", "User is not the Owner. Only Owner can update a project...");
            System.out.println("User is not the Owner. Only Owner can update a project.");
            return "redirect:/projects";
        }
    }



    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id, HttpSession session){

        String username = utilityService.getUsernameFromSession();
        ProjectDtls projectDtls = projectService.getProjectById(id);

        if(username.equals(projectDtls.getOwner_name()))
        {
            try{
                projectService.deleteProjectById(id);
                session.setAttribute("successmessage", "Project Deleted Successfully...");
                return "redirect:/projects";
            }catch (Exception e)
            {
                session.setAttribute("failuremessage", "Error Occurred...");
                return "redirect:/projects";
            }
        }
        else
        {
            session.setAttribute("failuremessage", "User is not the Owner. Only Owner can delete a project...");
            System.out.println("User is not the Owner. Only Owner can delete a project.");
        }
        return "redirect:/projects";
    }



    @GetMapping("/projects/addMember/{id}")
    public String addMemberForm(@PathVariable Long id, Model model, HttpSession session)
    {
        String username = utilityService.getUsernameFromSession();
        UserDtls userDtls = userService.getUserByUsername(username);
        ProjectDtls projectDtls = projectService.getProjectById(id);
        List<UserDtls> userDtlsSet = userService.getMembersByProject(id);

        if(username.equals(projectDtls.getOwner_name()) || userDtlsSet.contains(userDtls))
        {
            model.addAttribute("project", projectDtls);
            return "add_member";
        }
        else
        {
            session.setAttribute("failuremessage", "Only Owner and Members can add other Members...");
            System.out.println("Only Owner and Members can add other Members");
            return "redirect:/projects";
        }
    }



    @PostMapping("/projects/addMember/{id}")
    public String addMember(@PathVariable Long id, @RequestParam("username") String username,
                            Model model, HttpSession session)
    {
        if(username=="")
        {
            session.setAttribute("failuremessage", "Username is Required...");
            return "redirect:/projects";
        }
        UserDtls userDtls = userService.getUserByUsername(username);
        ProjectDtls projectDtls = projectService.getProjectById(id);
        List<UserDtls> userDtlsSet = userService.getMembersByProject(id);

        if(username.equals(projectDtls.getOwner_name()))
        {
            session.setAttribute("failuremessage", "Owner is already a Member...");
            System.out.println("Owner is already a member.");
        }
        else if(userDtlsSet.contains(userDtls))
        {
            session.setAttribute("failuremessage", "User is already a member of this project...");
            System.out.println("User is already a member of this project.");
        }
        else if(projectDtls.getNumberOfProjectMembers() > 4)
        {
            session.setAttribute("failuremessage", "Already 5 members. No more members can be added...");
            System.out.println("Already 5 members. No more members can be added.");
        }
        else
        {
            try {
                Set<ProjectDtls> st = userDtls.getProjectDtls();
                projectDtls.setNumberOfProjectMembers(projectDtls.getNumberOfProjectMembers()+1);
                projectService.saveProject(projectDtls);
                st.add(projectDtls);
                userDtls.setProjectDtls(st);
                userService.saveUser(userDtls);
                session.setAttribute("successmessage", "Member Added Successfully...");
                return "redirect:/projects";
            }catch (Exception e)
            {
                session.setAttribute("failuremessage", "Error Occurred...");
                return "redirect:/projects";
            }
        }

        return "redirect:/projects";
    }


//    @GetMapping("/projects/report")
//    public String generateReport(HttpSession session)
//            throws JRException, FileNotFoundException {
//        try {
//            reportService.exportReport();
//            session.setAttribute("successmessage", "Report Successfully Generated...");
//            return "redirect:/projects";
//        }catch (Exception e)
//        {
//            session.setAttribute("failuremessage", "Report Could not be Generated...");
//            return "redirect:/projects";
//        }
//    }


    @GetMapping("/projects/reportDateRange")
    public String reportDateForm()
    {
        return "report_daterange";
    }



    @GetMapping("/GET/api/v1/projectshtml")
    public String getProjectsHtml(Model model)
    {
        model.addAttribute("projectsList", projectService.getAllProjects());
        return "api_projects_list";
    }


    @PostMapping("/projects/report")
    public String generateReport(@ModelAttribute("dateConv") DateConv dateConv, HttpSession session)
            throws JRException, FileNotFoundException {
        try {
            reportService.exportReport(dateConv.getStart(), dateConv.getEnd());
            session.setAttribute("successmessage", "Report Successfully Generated...");
            return "redirect:/projects";
        } catch (Exception e) {
            session.setAttribute("failuremessage", "Report Could not be Generated...");
            return "redirect:/projects";
        }
    }

}


