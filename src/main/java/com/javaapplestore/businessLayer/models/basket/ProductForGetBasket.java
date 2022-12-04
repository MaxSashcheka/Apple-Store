package com.javaapplestore.businessLayer.models.basket;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductForGetBasket {
    private int id;
    private String name;
    private int price;
    private String type;
    private int countInBasket;
}
