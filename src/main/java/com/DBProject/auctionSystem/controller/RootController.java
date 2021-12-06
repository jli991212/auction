package com.DBProject.auctionSystem.controller;

import java.util.Optional;

import com.DBProject.auctionSystem.model.Member;
import com.DBProject.auctionSystem.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RootController {
    @Autowired
    MemberService memberService;

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

    @GetMapping(path = "/register")
    public ModelAndView registerMember(){
        Member member = new Member();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("member", member);
        modelAndView.setViewName("add_member");

        return modelAndView;
    }

    @PostMapping(path = "/register")
    public ModelAndView registerMember(@ModelAttribute Member member){
        memberService.addMember(member);
        return new ModelAndView("redirect:/");
    }
}
