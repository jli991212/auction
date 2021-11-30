package com.DBProject.auctionSystem.service;

import com.DBProject.auctionSystem.dao.BidDao;
import com.DBProject.auctionSystem.dao.ItemDao;
import com.DBProject.auctionSystem.dao.MemberDao;
import com.DBProject.auctionSystem.model.Bid;
import com.DBProject.auctionSystem.model.Item;
import com.DBProject.auctionSystem.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {
    @Autowired
    BidDao bidDao;
    @Autowired
    ItemDao itemDao;
    // bids
    public List<String> getAllBids() {
        String query1 = "select * from bid";
        return bidDao.getAllBids(query1);
    }
    public List<Bid> getBidsByBuyerID(int buyerID) {
        return bidDao.getBidsByBuyerID(buyerID);
    }
    public List<Bid> getBidsByItemID(int itemID) {
        return bidDao.getBidsByItemID(itemID);
    }
    public Bid addBid(Bid bid){
        return bidDao.addBid(bid);
    }
    public void setWinningBid(int itemID, int buyerID){
        bidDao.setWinningBid(itemID, buyerID);
    }

    // items
    public List<String> getAllItems() {
        String query1 = "select * from item";
        return itemDao.getAllItems(query1);
    }
    public List<Item> getItemsByItemID(int itemID){
        return itemDao.getItemByItemID(itemID);
    }
    public List<Item> getItemsBySellerID(int sellerID){
        return itemDao.getItemsBySellerID(sellerID);
    }
    public List<Item> getItemsByCategory(int categoryID){
        return itemDao.getItemsByCategory(categoryID);
    }
    public Item addItem(Item item){
        return itemDao.addItem(item);
    }
    public void updateItem(Item item, int itemID, int sellerID){
        itemDao.updateItem(item, itemID, sellerID);
    }
}
