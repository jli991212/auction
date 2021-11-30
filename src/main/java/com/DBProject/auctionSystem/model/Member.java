package com.DBProject.auctionSystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
    private Integer memberID;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String homeAddress;
    private String memberType;
}
