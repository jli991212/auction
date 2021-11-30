package com.DBProject.auctionSystem.service;

import com.DBProject.auctionSystem.dao.BidDao;
import com.DBProject.auctionSystem.dao.ItemDao;
import com.DBProject.auctionSystem.model.Bid;
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

    public boolean deleteBid(int itemID, int buyerID){
        return bidDao.deleteBid(itemID, buyerID);
    }

    public boolean setWinningBid(int itemID, int buyerID){
        return bidDao.setWinningBid(itemID, buyerID);
    }

    // items
    public List<String> getAllItems() {
        String query1 = "select * from item";
        return itemDao.getAllItems(query1);
    }
}
