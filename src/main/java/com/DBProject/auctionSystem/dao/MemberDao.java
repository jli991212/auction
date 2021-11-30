package com.DBProject.auctionSystem.dao;

import java.sql.ResultSet;
import java.util.List;

import com.DBProject.auctionSystem.model.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<String> getMembers() {
        String sql = "SELECT name FROM member";
        return jdbc.queryForList(sql, String.class);
    }

    public Member getMember(int memberID) {
        String sql = "SELECT name FROM member WHERE memberID=?";
        Member member = null;

        try {
            member = jdbc.queryForObject(sql, (ResultSet rs, int rowNum) -> {
                Member m = new Member();

                m.setName(rs.getString("name"));
                
                return m;
            }, memberID);
        } catch(Exception e) {}

        return member;
    }

    public Member addMember(Member member) {
        String sql = "INSERT INTO member (`name`, `email`, `password`, `memberType`) VALUES (?, ?, ?, ?)";

        int result = jdbc.update(
            sql,
            member.getName(),
            member.getEmail(),
            member.getPassword(),
            member.getMemberType()
        );

        return result > 0 ? member : null;
    }
}
