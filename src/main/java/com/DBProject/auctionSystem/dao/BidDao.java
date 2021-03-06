package com.DBProject.auctionSystem.dao;

import com.DBProject.auctionSystem.model.Bid;
import com.DBProject.auctionSystem.util.ResultMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BidDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<Bid> getAllBids() {
        String sql = "SELECT * FROM bid ORDER BY bidTime DESC";
        return new ResultMapper<Bid>().queryForList(jdbc, sql);
    }

    public List<Bid> getBidsByBuyerID(int buyerID) {
        String sql = "SELECT * FROM bid WHERE buyerID=?";
        return new ResultMapper<Bid>().queryForList(jdbc, sql, buyerID);
    }

    public List<Bid> getBidsByItemID(int itemID) {
        String sql = "SELECT * FROM bid WHERE itemID=?";
        return new ResultMapper<Bid>().queryForList(jdbc, sql, itemID);
    }

    public Bid addBid(Bid bid) {
        String sql = "INSERT INTO bid (`itemID`, `buyerID`, `bidPrice`, `bidTime`, `isWinner`) VALUES (?, ?, ?, ?, ?)";
        
        LocalDateTime bidTime = bid.getBidTime() != null ? bid.getBidTime() : LocalDateTime.now();
        Boolean isWinner = bid.getIsWinner() != null && bid.getIsWinner();

        int result = jdbc.update(
            sql,
            bid.getItemID(),
            bid.getBuyerID(),
            bid.getBidPrice(),
            bidTime,
            isWinner
        );

        return result > 0 ? bid : null;
    }

    public boolean deleteBid(Bid bid) {
        String sql = "DELETE FROM bid WHERE itemID = ? AND buyerID = ? AND bidPrice = ?";

        int result = jdbc.update(
            sql,
            bid.getItemID(),
            bid.getBuyerID(),
            bid.getBidPrice()
        );

        return result == 1;
    }
    
    //TODO: NEEDS FIXING - If winning buyer bids multiple times on an item, all their bids win
    public boolean setWinningBid(int itemID, int buyerID) {
        String sql = "UPDATE bid SET isWinner = true WHERE itemID = ? AND buyerID = ?";

        int result = jdbc.update(
            sql,
            itemID,
            buyerID
        );

        return result == 1;
    }

}
