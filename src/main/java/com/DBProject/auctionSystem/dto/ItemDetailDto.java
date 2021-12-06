package com.DBProject.auctionSystem.dto;

import com.DBProject.auctionSystem.model.Item;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDetailDto {
    @JsonUnwrapped
    private Item item;
    
    private String category;
    private String sellerName;
    private Double currentBid;
    private Integer totalBids;
}