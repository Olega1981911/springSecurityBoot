package com.project.springsecurityboot.controllers;


import com.project.springsecurityboot.models.Role;
import com.project.springsecurityboot.security.PersonDetails;
import com.project.springsecurityboot.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RestController
@RequestMapping("/")
public class GreetingsControllers {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public GreetingsControllers(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public String start() {
        return "/index";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getUser());
        return "/index";
    }

    @GetMapping("/user")
    public String pageForUserProfile(Model model, Principal principal) {
        model.addAttribute("user", userDetailsService.findByName(principal.getName()));

        return "user";
    }
}
