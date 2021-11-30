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
public class Bid {
    private int itemID;
    private int buyerID;
    private double bidPrice;
    private Date bidTime;
    private boolean isWinner;
}
