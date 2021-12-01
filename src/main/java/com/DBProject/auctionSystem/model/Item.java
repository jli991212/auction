package com.DBProject.auctionSystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

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
    private Date bidStartDate;
    private Date bidEndDate;
    private Integer categoryID;
    private String size;
}
