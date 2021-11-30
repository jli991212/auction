package com.DBProject.auctionSystem.controller;

import java.util.List;

import com.DBProject.auctionSystem.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping
    public List<String> index() {
        return memberService.getMembers();
    }

    @PostMapping(path = "/add")
    public List<String> add() {
        return memberService.addMember();
    }
}
