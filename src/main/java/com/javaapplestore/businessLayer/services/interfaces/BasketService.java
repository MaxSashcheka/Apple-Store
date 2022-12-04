package com.javaapplestore.businessLayer.services.interfaces;

import com.javaapplestore.businessLayer.models.basket.GetBasket;

import javax.naming.AuthenticationException;

public interface BasketService {
    GetBasket getBasket(int userId);

    void addProductToBasket(int userId, int productId) throws Exception;
    void changeProductCount(int userId, int productId, int count) throws AuthenticationException;
    void deleteBookFromBasket(int userId, int productId) throws Exception;
}
