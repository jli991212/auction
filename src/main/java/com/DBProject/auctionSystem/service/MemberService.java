package com.DBProject.auctionSystem.service;

import java.util.List;

import com.DBProject.auctionSystem.dao.AdminDao;
import com.DBProject.auctionSystem.dao.BuyerDao;
import com.DBProject.auctionSystem.dao.MemberDao;
import com.DBProject.auctionSystem.dao.SellerDao;
import com.DBProject.auctionSystem.model.Admin;
import com.DBProject.auctionSystem.model.Buyer;
import com.DBProject.auctionSystem.model.Member;

import com.DBProject.auctionSystem.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;
    @Autowired
    AdminDao adminDao;
    @Autowired
    BuyerDao buyerDao;
    @Autowired
    SellerDao sellerDao;

    public List<Member> getMembers() {
        return memberDao.getMembers();
    }

    public Member getMember(int memberID) {
        return memberDao.getMember(memberID);
    }

    public Member getMemberByEmail(String email) {
        return memberDao.getMemberByEmail(email);
    }

    public String verifyMember(String email, String password){
        return memberDao.verifyMember(email, password);
    }

    public Member addMember(Member member) {
        return memberDao.addMember(member);
    }
    public Seller addSeller(Seller seller) {
        return sellerDao.addSeller(seller);
    }
    public Buyer addBuyer(Buyer buyer) {
        return buyerDao.addBuyer(buyer);
    }
    public Admin addAdmin(Admin admin) {
        return adminDao.addAdmin(admin);
    }

    public boolean deleteMember(int memberID) {
        return memberDao.deleteMember(memberID);
    }

    // admin
    public List<Admin> getAdmins(){
        return adminDao.getAdmins();
    }

    // buyer
    public List<Buyer> getBuyers(){
        return buyerDao.getBuyers();
    }

    // seller
    public List<Seller> getSellers(){
        return sellerDao.getSellers();
    }
}
