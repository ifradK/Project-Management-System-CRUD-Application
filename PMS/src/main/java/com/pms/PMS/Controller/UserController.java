package com.pms.PMS.Controller;

import com.pms.PMS.Entity.UserDtls;
import com.pms.PMS.Repository.UserRepository;
import com.pms.PMS.Service.ProjectService;
import com.pms.PMS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @Autowired
    private BCryptPasswordEncoder bp;

    @GetMapping("/")
    public String Home()
    {
        return "register";
    }

//    @GetMapping("/login")
//    public String login()
//    {
//        return "login";
//    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }



//    @PostMapping("/dologin")
//    public void dologin(@ModelAttribute UserDtls u, HttpSession session)
//    {
//        System.out.println("FIRED");
//        System.out.println(u);
//    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDtls userDtls, HttpSession session)
    {
        try {
            userDtls.setRole("ROLE_USER");
            userDtls.setPassword(bp.encode(userDtls.getPassword()));

            userService.saveUser(userDtls);
            session.setAttribute("successmessage", "User Successfully Registered...");
            return "redirect:/";
        }
        catch(Exception e) {
            session.setAttribute("failuremessage", "User Could not be Registered...");
            return "redirect:/";
        }
    }

}
