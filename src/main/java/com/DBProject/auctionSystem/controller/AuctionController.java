package com.DBProject.auctionSystem.controller;

import com.DBProject.auctionSystem.dto.ItemDetailDto;
import com.DBProject.auctionSystem.model.Bid;
import com.DBProject.auctionSystem.model.Item;
import com.DBProject.auctionSystem.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/auctions")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    @Autowired 
    HttpSession httpSession;

    @GetMapping
    public ModelAndView allAuctionItems() {
        ModelAndView model = new ModelAndView();
        List<ItemDetailDto> itemlist = this.getAllItems();
        
        model.addObject("itemlists", itemlist);
        model.setViewName("item_list");
        
        return model;
    }
    
    // bids
    @GetMapping("/bid/{itemID}")
    @PreAuthorize("hasAuthority('buyer')")
    public ModelAndView addbidForm(@PathVariable int itemID) {
        ModelAndView model = new ModelAndView();
        Bid bid = new Bid();

        ItemDetailDto item = this.getItemByItemID(itemID);

        if(item == null)
            return new ModelAndView("redirect:/auctions");

        bid.setItemID(itemID);
        bid.setBuyerID((Integer) httpSession.getAttribute("memberID"));

        model.addObject("item", item);
        model.addObject("bid", bid);
        model.setViewName("add_bid");

        return model;
    }

    @PostMapping("/addbid")
    public ModelAndView greetingSubmit(@ModelAttribute Bid bid) {
        this.addBid(bid);
        return new ModelAndView("redirect:/auctions/bids/all");
    }

    @GetMapping(path = "/bids/all")
    public ModelAndView allBids() {
        ModelAndView model = new ModelAndView();
        List<Bid> bidlist = this.getAllBids();

        model.addObject("bidLists", bidlist);
        model.setViewName("bid_list");

        return model;
    }

    @GetMapping(path = "/bids")
    public List<Bid> getAllBids() {
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
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping(path = "/delete/{itemID}")
    public ModelAndView deleteItem(@PathVariable int itemID) {
        auctionService.deleteItem(itemID);
        return new ModelAndView("redirect:/auctions");
    }
    
    @GetMapping(path = "/items")
    public List<ItemDetailDto> getAllItems() {
        return auctionService.getAllItems();
    }

    @GetMapping(path = "/items/{itemID}")
    public ItemDetailDto getItemByItemID(@PathVariable int itemID){
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

    @PostMapping(path = "/items/add")
    public Item addItem(@RequestBody Item item){
        return auctionService.addItem(item);
    }

    @PostMapping(path = "/items/update/{itemID}-{sellerID}")
    public void updateItem(@RequestBody Item item,@PathVariable int itemID, @PathVariable int sellerID){
        auctionService.updateItem(item, itemID, sellerID);
    }
}
