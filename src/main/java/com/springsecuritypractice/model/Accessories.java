package com.springsecuritypractice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Accessories {
    private int accessoriesId;
    private String accessoriesName;
    private int quantity;
    private double price;
}
