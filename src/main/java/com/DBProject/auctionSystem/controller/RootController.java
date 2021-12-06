package com.DBProject.auctionSystem.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RootController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        
        model.setViewName("index");

        return model;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView model = new ModelAndView();

        System.out.println(auth);

        if(auth == null || auth instanceof AnonymousAuthenticationToken) {
            model.setViewName("login");
        } else {
            model.setViewName("redirect:/");
        }

        return model;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        SecurityContextHolder.getContext().setAuthentication(null);

        return new ModelAndView("redirect:/");
    }
}
