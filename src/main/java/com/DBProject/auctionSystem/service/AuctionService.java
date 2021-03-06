package com.DBProject.auctionSystem.service;

import com.DBProject.auctionSystem.dao.BidDao;
import com.DBProject.auctionSystem.dao.ItemDao;
import com.DBProject.auctionSystem.dto.ItemDetailDto;
import com.DBProject.auctionSystem.model.Bid;
import com.DBProject.auctionSystem.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {
    @Autowired
    BidDao bidDao;
    
    @Autowired
    ItemDao itemDao;

    public List<Bid> getAllBids() {
        return bidDao.getAllBids();
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

    public boolean deleteBid(Bid bid){
        return bidDao.deleteBid(bid);
    }

    public boolean setWinningBid(int itemID, int buyerID){
        return bidDao.setWinningBid(itemID, buyerID);
    }

    // items
    public List<ItemDetailDto> getAllItems() {
        return itemDao.getAllItems();
    }

    public ItemDetailDto getItemByItemID(int itemID){
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

    public boolean deleteItem(int itemID){
        return itemDao.deleteItem(itemID);
    }
}
