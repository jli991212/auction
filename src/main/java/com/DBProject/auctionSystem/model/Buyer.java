package com.DBProject.auctionSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Buyer extends Member {
    private int memberID;
    private String shippingAddress;
}
