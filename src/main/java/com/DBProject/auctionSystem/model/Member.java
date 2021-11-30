package com.DBProject.auctionSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private int memberID;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String homeAddress;
    private String memberType;
}
