package com.javaapplestore.businessLayer.models.basket;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class GetBasket {
    private ArrayList<ProductForGetBasket> products = new ArrayList<>();
}
