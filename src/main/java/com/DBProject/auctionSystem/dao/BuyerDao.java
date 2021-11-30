package com.DBProject.auctionSystem.dao;

import com.DBProject.auctionSystem.model.Admin;
import com.DBProject.auctionSystem.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BuyerDao {
    @Autowired
    JdbcTemplate jdbc;
    public List<Buyer> getBuyers() {
        String sql = "SELECT * FROM buyer";
        List<Buyer> buyer = null;

        try {
            buyer = jdbc.query(sql, (rs, rowNum) ->
                    new Buyer(
                            rs.getInt("memberID"),
                            rs.getString("shippingAddress")
                    ));
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("find buyer error");
        }

        return buyer;
    }
}
