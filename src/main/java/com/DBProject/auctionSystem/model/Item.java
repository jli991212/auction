package com.DBProject.auctionSystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
    private Integer itemID;
    private Integer sellerID;
    private String itemName;
    private String description;
    private String image;
    private Double startingBid;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime bidStartDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime bidEndDate;
    
    private Integer categoryID;
    private String size;
}
