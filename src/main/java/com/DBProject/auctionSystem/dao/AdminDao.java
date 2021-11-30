package com.DBProject.auctionSystem.dao;

import com.DBProject.auctionSystem.model.Admin;
import com.DBProject.auctionSystem.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminDao {
    @Autowired
    JdbcTemplate jdbc;
    public List<Admin> getAdmins() {
        String sql = "SELECT * FROM admin";
        List<Admin> admin = null;

        try {
            admin = jdbc.query(sql, (rs, rowNum) ->
                    new Admin(
                            rs.getInt("memberID"),
                            rs.getBoolean("approved")
                    ));
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("find admins error");
        }

        return admin;
    }
}
