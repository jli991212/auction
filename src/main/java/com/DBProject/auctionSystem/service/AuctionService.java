package com.DBProject.auctionSystem.service;

import com.DBProject.auctionSystem.dao.BidDao;
import com.DBProject.auctionSystem.dao.MemberDao;
import com.DBProject.auctionSystem.model.Bid;
import com.DBProject.auctionSystem.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {
    @Autowired
    BidDao bidDao;

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
}
