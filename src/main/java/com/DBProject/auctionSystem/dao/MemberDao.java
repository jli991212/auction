package com.DBProject.auctionSystem.dao;

import java.util.List;

import com.DBProject.auctionSystem.model.Member;
import com.DBProject.auctionSystem.util.ResultMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<Member> getMembers() {
        String sql = "SELECT * FROM member";
        return new ResultMapper<Member>().queryForList(jdbc, sql);
    }

    public Member getMember(int memberID) {
        String sql = "SELECT name FROM member WHERE memberID=?";
        return new ResultMapper<Member>().queryForObject(Member.class, jdbc, sql, memberID);
    }

    public Member getMemberByEmail(String email) {
        String sql = "SELECT * FROM member WHERE email=?";
        return new ResultMapper<Member>().queryForObject(Member.class, jdbc, sql, email);
    }

    public String verifyMember(String email, String password){
        Member member = getMemberByEmail(email);
        
        if(member == null || !member.getPassword().equals(password)){
            return "Login information does not match!";
        }

        return member.getMemberType();
    }

    public Member addMember(Member member) {
        String sql = "INSERT INTO member (`name`, `email`, `password`, `phoneNumber`, `homeAddress`, `memberType`) VALUES (?, ?, ?, ?, ?, ?)";

        int result = jdbc.update(
            sql,
            member.getName(),
            member.getEmail(),
            member.getPassword(),
            member.getPhoneNumber(),
            member.getHomeAddress(),
            member.getMemberType()
        );

        if(result > 0) {
            return this.getMemberByEmail(member.getEmail());
        }

        return null;
    }

    public boolean deleteMember(int memberID) {
        String sql = "DELETE FROM member WHERE memberID = ?";
        int result = jdbc.update(sql, memberID);

        return result == 1;
    }
}
