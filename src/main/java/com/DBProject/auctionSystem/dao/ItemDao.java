package com.DBProject.auctionSystem.dao;

import com.DBProject.auctionSystem.model.Bid;
import com.DBProject.auctionSystem.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<String> getAllItems(String query){
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

    public List<Item> getItemByID(int itemID) {
        String sql = "SELECT * FROM item WHERE itemID=" + itemID;
        List<Item> item = null;

        try {
            item = jdbc.query(sql, (rs, rowNum) ->
                    new Item(
                            rs.getInt("itemID"),
                            rs.getInt("sellerID"),
                            rs.getString("itemName"),
                            rs.getString("description"),
                            rs.getDouble("startingBid"),
                            rs.getTimestamp("bidStartDate").toLocalDateTime(),
                            rs.getTimestamp("bidEndDate").toLocalDateTime(),
                            rs.getInt("categoryID"),
                            rs.getString("size")
                    ));
        } catch(Exception e) {
            System.out.println("find by item error");
        }

        return item;
    }

    public void getItemsBySellerID(int sellerID) {
        //
    }

    public void getItemsByCategory(int categoryName) {
        //
    }

    public void getTopItems(int categoryName) {
        //
    }

    public void addItem(Item item) {
        //
    }

    public void updateItem(Item item) {
        //
    }

    public void deleteItem(Item item) {
        //
    }

}
