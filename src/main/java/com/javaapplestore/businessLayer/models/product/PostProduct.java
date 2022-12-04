package com.javaapplestore.businessLayer.models.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostProduct {
    private String name;
    private int price;
    private int productTypeId;
}
