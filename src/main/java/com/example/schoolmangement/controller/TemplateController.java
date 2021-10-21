package com.example.schoolmangement.controller;

import com.example.schoolmangement.model.Users;
import com.example.schoolmangement.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class TemplateController {

    private final UserDetailsServiceImpl userDetailsService;


    @Autowired
    public TemplateController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("index")
    public String homePage() {
        return "index";
    }

    @GetMapping("login")
    public String loginPage(Model model) {
        model.addAttribute("login", new Users());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/home/index";
    }
    @PostMapping("login")
    public String login(@ModelAttribute("login") Users users) {
        userDetailsService.loadUserByUsername(users.getUsername());
        return "redirect:/home/index";
    }
//
//    @GetMapping("admin")
//    public String adminLogin(Model model) {
//        model.addAttribute("adminLogin", new User());
//        return "adminLogin";
//    }
//
//    @PostMapping("admin")
//    public String adminLoginPost(@ModelAttribute("adminLogin") User user) {
////        staffService.loadUserByUsername(staffClass.getUsername());
//        userDetailsService.loadUserByUsername(user.getUsername());
//        return "redirect:/school/allStaff";
//    }
    @PostMapping("logout")
    public String loginOut() {
        return "redirect:/home/index";
    }
}
