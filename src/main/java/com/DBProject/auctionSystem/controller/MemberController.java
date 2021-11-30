package com.DBProject.auctionSystem.controller;

import java.util.List;

import com.DBProject.auctionSystem.model.Admin;
import com.DBProject.auctionSystem.model.Buyer;
import com.DBProject.auctionSystem.model.Member;
import com.DBProject.auctionSystem.model.Seller;
import com.DBProject.auctionSystem.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Member add(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @GetMapping(path = "/{memberID}")
    public Member get(@PathVariable int memberID) {
        return memberService.getMember(memberID);
    }

    @GetMapping(path = "/email/{email}")
    public List<Member> getMemberByEmail(@PathVariable String email) {
        return memberService.getMemberByEmail(email);
    }

    @GetMapping(path = "/verify/{email}-{password}")
    public String verifyMember(@PathVariable String email, @PathVariable String password) {
        return memberService.verifyMember(email, password);
    }

    @GetMapping(path = "/info")
    public List<String> getInfo() {// could do @PathVariable
        return memberService.getMemberInfo();
    }

    // admin
    @GetMapping(path = "/admins")
    public List<Admin> getAdmins(){
        return memberService.getAdmins();
    }

    // buyer
    @GetMapping(path = "/buyers")
    public List<Buyer> getBuyers(){
        return memberService.getBuyers();
    }

    // seller
    @GetMapping(path = "/sellers")
    public List<Seller> getSellers(){
        return memberService.getSellers();
    }
}
