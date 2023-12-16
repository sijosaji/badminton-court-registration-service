package com.justplay.badminton.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID userId;
    private String userName;
    private String contactNo;
    private String userMailId;
}
