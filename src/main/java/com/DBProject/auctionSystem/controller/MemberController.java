package com.DBProject.auctionSystem.controller;

import java.util.List;

import com.DBProject.auctionSystem.model.Admin;
import com.DBProject.auctionSystem.model.Buyer;
import com.DBProject.auctionSystem.model.Member;
import com.DBProject.auctionSystem.model.Seller;
import com.DBProject.auctionSystem.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(path="/all")
    public ModelAndView allmembers() {
        ModelAndView model = new ModelAndView();
        List<Member> memberlist = this.index();

        model.addObject("memberlists", memberlist);
        model.setViewName("member_list");
        
        return model;
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
        ModelAndView model = new ModelAndView();
        member = memberService.addMember(member);
        String memberType = member != null ? member.getMemberType() : "";

        model.addObject("memberType", memberType);

        switch(memberType) {
            case "buyer":
                Buyer buyer = new Buyer();
                
                buyer.setMemberID(member.getMemberID());
                model.addObject("buyer", buyer);

                model.setViewName("add_member");

                return model;
            case "seller":
                Seller seller = new Seller();
                
                seller.setMemberID(member.getMemberID());
                model.addObject("seller", seller);

                model.setViewName("add_member");

                return model;
            case "admin":
                Admin admin = new Admin();

                admin.setMemberID(member.getMemberID());
                memberService.addAdmin(admin);
                return new ModelAndView("redirect:/login");
            default:
                break;
        }

        model.addObject("error", true);
        return model;
    }
    
    @PostMapping(path = "/sellers/register")
    public ModelAndView registerSeller(@ModelAttribute Seller seller){
        ModelAndView model = new ModelAndView();
        seller = memberService.addSeller(seller);

        if(seller != null) 
            return new ModelAndView("redirect:/login");

        model.addObject("memberType", "seller");
        model.addObject("seller", seller);
        model.addObject("error", true);

        return model;
    }
    @PostMapping(path = "/buyers/register")
    public ModelAndView registerBuyer(@ModelAttribute Buyer buyer){
        ModelAndView model = new ModelAndView();
        buyer = memberService.addBuyer(buyer);

        if(buyer != null) 
            return new ModelAndView("redirect:/login");

        model.addObject("memberType", "buyer");
        model.addObject("buyer", buyer);
        model.addObject("error", true);

        return model;
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping(path = "/delete/{memberID}")
    public ModelAndView deleteMember(@PathVariable int memberID) {
        memberService.deleteMember(memberID);
        return new ModelAndView("redirect:/members/all");
    }

    @GetMapping
    public List<Member> index() {
        return memberService.getMembers();
    }
    
    @GetMapping(path = "/{memberID}")
    public Member get(@PathVariable int memberID) {
        return memberService.getMember(memberID);
    }

    @GetMapping(path = "/email/{email}")
    public Member getMemberByEmail(@PathVariable String email) {
        return memberService.getMemberByEmail(email);
    }
    
    @GetMapping(path = "/verify/{email}-{password}")
    public String verifyMember(@PathVariable String email, @PathVariable String password) {
        return memberService.verifyMember(email, password);
    }

    @PostMapping(path = "/add")
    public Member add(@RequestBody Member member) {
        return memberService.addMember(member);
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
