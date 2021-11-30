package com.DBProject.auctionSystem.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private int itemID;
    private int sellerID;
    private int categoryID;
    private String itemName;
    private String description;
    private double startingBid;
    private Date bidStartDate;
    private Date bidEndDate;
    private String size;
}
