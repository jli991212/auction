package com.DBProject.auctionSystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bid {
    private Integer itemID;
    private Integer buyerID;
    private Double bidPrice;
    private LocalDateTime bidTime;
    private Boolean isWinner;
}
