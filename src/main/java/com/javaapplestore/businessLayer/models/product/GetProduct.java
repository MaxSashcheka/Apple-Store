package com.javaapplestore.businessLayer.models.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProduct {
    private int id;
    private String name;
    private int price;
    private ProductTypeForGetProduct productType;
}
