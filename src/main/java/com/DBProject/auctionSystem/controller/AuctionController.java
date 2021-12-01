package com.DBProject.auctionSystem.controller;

import com.DBProject.auctionSystem.model.Bid;
import com.DBProject.auctionSystem.model.Item;
import com.DBProject.auctionSystem.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/auctions")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    // bids
    @GetMapping(path = "/bids/allBids")
    public List<String> getAllBids() { // could do @PathVariable
        return auctionService.getAllBids();
    }
    
    @GetMapping(path = "/bids/buyer/{buyerID}")
    public List<Bid> getBuyer(@PathVariable int buyerID) {
        return auctionService.getBidsByBuyerID(buyerID);
    }

    @GetMapping(path = "/bids/item/{itemID}")
    public List<Bid> getItem(@PathVariable int itemID) {
        return auctionService.getBidsByItemID(itemID);
    }

    @PostMapping(path = "/bids/add")
    public Bid addBid(@RequestBody Bid bid) {
        return auctionService.addBid(bid);
    }

    @PostMapping(path = "/bids/setWinner/{itemID}-{buyerID}")
    public boolean setWinningBid(@PathVariable int itemID, @PathVariable int buyerID){
        return auctionService.setWinningBid(itemID, buyerID);
    }

    @DeleteMapping(path = "/bids/delete/{itemID}-{buyerID}")
    public boolean deleteBid(@PathVariable int itemID, @PathVariable int buyerID) {
        return auctionService.deleteBid(itemID, buyerID);
    }

    // items
    @GetMapping(path = "/items/allItemsString")
    public List<String> getAllItemsString() { // could do @PathVariable
        return auctionService.getAllItemsString();
    }
    @GetMapping(path = "/items/allItems")
    public List<Item> getAllItems() {
        return auctionService.getAllItems();
    }
    @GetMapping(path = "/items/item/{itemID}")
    public List<Item> getItemByItemID(@PathVariable int itemID){
        return auctionService.getItemByItemID(itemID);
    }
    @GetMapping(path = "/items/seller/{sellerID}")
    public List<Item> getItemsBySellerID(@PathVariable int sellerID){
        return auctionService.getItemsBySellerID(sellerID);
    }
    @GetMapping(path = "/items/category/{categoryID}")
    public List<Item> getItemsByCategory(@PathVariable int categoryID){
        return auctionService.getItemsByCategory(categoryID);
    }
    @PostMapping(path = "/items/addItem")
    public Item addItem(@RequestBody Item item){
        return auctionService.addItem(item);
    }
    @PostMapping(path = "/items/updateItem/{itemID}-{sellerID}")
    public void updateItem(@RequestBody Item item,@PathVariable int itemID, @PathVariable int sellerID){
        auctionService.updateItem(item, itemID, sellerID);
    }

}
