package com.DBProject.auctionSystem.dao;

import java.util.List;

import com.DBProject.auctionSystem.model.Member;
import com.DBProject.auctionSystem.model.Seller;

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

    public void addMember(String type) {
        String sql = String.join("\n",
            "INSERT INTO member (",
            "`name`, `email`, `password`, `memberType`",
            ") VALUES (?, ?, ?, ?)"
        );

        jdbc.update(sql, "new name", "new@example.com", "123", type);
    }
}
