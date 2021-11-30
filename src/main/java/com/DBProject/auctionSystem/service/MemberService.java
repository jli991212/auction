package com.DBProject.auctionSystem.service;

import java.util.List;

import com.DBProject.auctionSystem.dao.MemberDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;

    public List<String> getMembers() {
        return memberDao.getMembers();
    }

    public List<String> addMember() {
        String x = "admin";

        memberDao.addMember(x);

        switch(x) {
            case "admin":
                break;
            case "buyer":
                break;
            case "seller":
                break;
            default:
                break;
        }

        return memberDao.getMembers();
    }
}
