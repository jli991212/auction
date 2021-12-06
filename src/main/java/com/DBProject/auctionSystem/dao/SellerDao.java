package com.DBProject.auctionSystem.dao;

import com.DBProject.auctionSystem.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SellerDao {
    @Autowired
    JdbcTemplate jdbc;
    public List<Seller> getSellers() {
        String sql = "SELECT * FROM seller";
        List<Seller> seller = null;

        try {
            seller = jdbc.query(sql, (rs, rowNum) ->
                    new Seller(
                            rs.getInt("memberID"),
                            rs.getString("bankAccount"),
                            rs.getString("routingNumber")
                    ));
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("find sellers error");
        }

        return seller;
    }
}
