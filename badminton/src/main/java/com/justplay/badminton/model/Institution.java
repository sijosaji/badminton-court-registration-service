package com.justplay.badminton.model;

import java.util.List;
import java.util.UUID;

public class Institution {
    private UUID institutionId;
    private String institutionName;
    private String ownerName;
    private String ownerContactNo;
    private String gstNo;
    private List<Court> courtList;
}
