package com.DBProject.auctionSystem.dao;

import com.DBProject.auctionSystem.dto.ItemDetailDto;
import com.DBProject.auctionSystem.model.Item;
import com.DBProject.auctionSystem.util.ResultMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<ItemDetailDto> getAllItems(){
        String sql = "SELECT * FROM item_detail";
        return new ResultMapper<ItemDetailDto>().queryForList(jdbc, sql);
    }

    public ItemDetailDto getItemByItemID(int itemID) {
        String sql = "SELECT * FROM item_detail WHERE itemID=?";
        return new ResultMapper<ItemDetailDto>().queryForObject(ItemDetailDto.class, jdbc, sql, itemID);
    }

    public List<Item> getItemsBySellerID(int sellerID) {
        String sql = "SELECT * FROM item WHERE sellerID=?";
        return new ResultMapper<Item>().queryForList(jdbc, sql, sellerID);
    }

    public List<Item> getItemsByCategory(int categoryID) {
        String sql = "SELECT * FROM item WHERE categoryID=?";
        return new ResultMapper<Item>().queryForList(jdbc, sql, categoryID);
    }

    public void getTopItems(int categoryName) {
        //
    }

    public Item addItem(Item item) {
        String sql = "INSERT INTO item (`itemID`, `sellerID`, `itemName`, `description`, `startingBid`, `bidStartDate`, `bidEndDate`, `categoryID`, `size`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int result = jdbc.update(
                sql,
                item.getItemID(),
                item.getSellerID(),
                item.getItemName(),
                item.getDescription(),
                item.getStartingBid(),
                item.getBidStartDate(),
                item.getBidEndDate(),
                item.getCategoryID(),
                item.getSize()
        );
        
        if(result != 1){
            System.out.println("add item error");
            return null;
        }

        return item;
    }

    public void updateItem(Item item, int itemID, int sellerID) {
        String sql = "UPDATE item SET itemName = ?, description = ?, startingBid = ?, bidStartDate = ?, bidEndDate = ?, categoryID = ?, size = ? WHERE itemID = ? AND sellerID = ? ";

        int result = jdbc.update(
                sql,
                item.getItemName(),
                item.getDescription(),
                item.getStartingBid(),
                item.getBidStartDate(),
                item.getBidEndDate(),
                item.getCategoryID(),
                item.getSize(),
                itemID,
                sellerID
        );

        if(result != 1) {
            System.out.println("update item error");
        }
    }

    public void deleteItem(Item item) {
        //
    }

}
