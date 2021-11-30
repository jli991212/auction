package com.DBProject.auctionSystem.service;

import java.util.List;

import com.DBProject.auctionSystem.dao.MemberDao;
import com.DBProject.auctionSystem.model.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;

    public List<String> getMembers() {
        return memberDao.getMembers();
    }

    public Member getMember(int memberID) {
        return memberDao.getMember(memberID);
    }

    public Member addMember(Member member) {
        String memberType = member.getMemberType();

        member = memberDao.addMember(member);

        switch(memberType) {
            case "admin":
                break;
            case "buyer":
                break;
            case "seller":
                break;
            default:
                break;
        }

        return member;
    }
}
