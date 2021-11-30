package com.DBProject.auctionSystem.dao;

import com.DBProject.auctionSystem.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BidDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<String> getAllBids(String query) {
        //
        try {
            DataSource ds = jdbc.getDataSource();
            Connection myConn = DataSourceUtils.getConnection(ds);
            Statement stmt = myConn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            // column names and count
            ResultSetMetaData rsMetaData = res.getMetaData();
            //Retrieving the list of column names
            int count = rsMetaData.getColumnCount();
            List<String> colNames = new ArrayList<>();
            for(int i = 1; i<=count; i++) {
                colNames.add(rsMetaData.getColumnName(i));
            }
            // get result
            List<String> result = new ArrayList<>();
            while(res.next()){ // each record
                StringBuilder ret = new StringBuilder();
                for(String s : colNames){
                    ret.append(s + ": " + res.getString(s)+", ");
                }
                result.add(ret.toString());
            }
            return result;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Bid> getBidsByBuyerID(int buyerID) {
        String sql = "SELECT * FROM bid WHERE buyerID="+buyerID;
        List<Bid> bid = null;

        try {
            bid = jdbc.query(sql, (rs, rowNum) ->
                    new Bid(
                        rs.getInt("itemID"),
                        rs.getInt("buyerID"),
                        rs.getDouble("bidPrice"),
                        rs.getTimestamp("bidTime").toLocalDateTime(),
                        rs.getBoolean("isWinner")
                    ));
        } catch(Exception e) {
            System.out.println("find by buyer error");
        }

        return bid;
    }

    public List<Bid> getBidsByItemID(int itemID) {
        String sql = "SELECT * FROM bid WHERE itemID="+itemID;
        List<Bid> bid = null;

        try {
            bid = jdbc.query(sql, (rs, rowNum) ->
                    new Bid(
                        rs.getInt("itemID"),
                        rs.getInt("buyerID"),
                        rs.getDouble("bidPrice"),
                        rs.getTimestamp("bidTime").toLocalDateTime(),
                        rs.getBoolean("isWinner")
                    ));
        } catch(Exception e) {
            System.out.println("find by item error");
        }

        return bid;
    }

    public Bid addBid(Bid bid) {
        String sql = "INSERT INTO bid (`itemID`, `buyerID`, `bidPrice`, `bidTime`, `isWinner`) VALUES (?, ?, ?, ?, ?)";
        
        LocalDate bidTime = bid.getBidTime() != null ? bid.getBidTime().toLocalDate() : LocalDate.now();
        Boolean isWinner = bid.getIsWinner() != null ? bid.getIsWinner() : false;

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

    
    public boolean deleteBid(int itemID, int buyerID) {
        String sql = "DELETE FROM bid WHERE itemID = ? AND buyerID = ?";

        int result = jdbc.update(
            sql,
            itemID,
            buyerID
        );

        return result == 1;
    }
    
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
