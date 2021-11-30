package com.DBProject.auctionSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private int senderID;
    private int receiverID;
    private int itemID;
    private double rating;
    private String comment;
}