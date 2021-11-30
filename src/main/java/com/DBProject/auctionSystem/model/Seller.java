package com.DBProject.auctionSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller extends Member {
    private int memberID;
    private String bankAccount;
    private String routingNumber;
}