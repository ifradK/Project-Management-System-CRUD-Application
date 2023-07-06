package com.pms.PMS.Controller;

import com.pms.PMS.Entity.UserDtls;
import com.pms.PMS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bp;

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }



    @GetMapping("/")
    public String Home()
    {
        return "landing_page";
    }



    @GetMapping("/register")
    public String register()
    {
        return "register";
    }



    @PostMapping("/register")
    public String register(@ModelAttribute UserDtls userDtls, HttpSession session)
    {
        try {
            if(userDtls.getUsername()=="" || userDtls.getEmail()=="" || userDtls.getPassword()=="")
            {
                session.setAttribute("failuremessage", "User Could not be Registered. Fill up all the fields");
                return "redirect:/register";
            }
            else
            {
                userDtls.setRole("ROLE_USER");
                userDtls.setPassword(bp.encode(userDtls.getPassword()));

                userService.saveUser(userDtls);
                session.setAttribute("successmessage", "User Successfully Registered...");
                return "redirect:/register";
            }
        }
        catch(Exception e) {
            session.setAttribute("failuremessage", "User Could not be Registered...");
            return "redirect:/register";
        }
    }


    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @PostMapping("/login")
    public String dologin()
    {
        return "projects";
    }

//    @PostMapping("/dologin")
//    public void dologin(@ModelAttribute UserDtls u, HttpSession session)
//    {
//        System.out.println("FIRED");
//        System.out.println(u);
//    }

}
