package com.DBProject.auctionSystem.config;

import javax.servlet.http.HttpSession;

import com.DBProject.auctionSystem.model.Member;
import com.DBProject.auctionSystem.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthContextConfiguration implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
    @Autowired 
    HttpSession httpSession;

    @Autowired
    MemberService memberService;
    
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        System.out.println("User Logged In");
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberService.getMemberByEmail(email);

        httpSession.setAttribute("memberID", member.getMemberID());
    }
}