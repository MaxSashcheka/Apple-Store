package com.javaapplestore.businessLayer.services;

import com.javaapplestore.businessLayer.models.basket.GetBasket;
import com.javaapplestore.businessLayer.models.basket.ProductForGetBasket;
import com.javaapplestore.businessLayer.repositories.BasketRepository;
import com.javaapplestore.businessLayer.repositories.ProductRepository;
import com.javaapplestore.businessLayer.repositories.UserRepository;
import com.javaapplestore.businessLayer.services.interfaces.BasketService;
import com.javaapplestore.entities.BasketEntity;
import com.javaapplestore.entities.BasketIdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;


@Service
public class BasketServiceImpl implements BasketService {

//    @Autowired
//    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public GetBasket getBasket(int userId) {
        var products = basketRepository.findByUserId(userId);
        var basket = new GetBasket();
        if (products.isEmpty()) return basket;
        for (var it : products) {
            var newProduct = new ProductForGetBasket();

            var product = it.getProduct();

//            if (book.getCount() < it.getCount()) {
//                it.setCount(book.getCount());
//                orderRepository.save(it);
//            }

            newProduct.setId(product.getId());
            newProduct.setName(product.getName());
            newProduct.setPrice(product.getPrice());
            newProduct.setType(product.getProductType().name);
            newProduct.setCountInBasket(1);

            basket.getProducts().add(newProduct);
        }

        return basket;
    }

    public void addProductToBasket(int userId, int productId) throws Exception {
        var user = userRepository.findById(userId);
        var product = productRepository.findById(productId);
        var existBaskets = basketRepository.findByUserId(userId);

        var basket = new BasketEntity();
        var id = new BasketIdEntity(product.get().getId(), user.get().getId());
        basket.setId(id);
        basket.setProduct(product.get());
        basket.setUser(user.get());
        basketRepository.save(basket);
    }

    @Override
    public void changeProductCount(int userId, int productId, int count) throws AuthenticationException {

    }

    @Override
    public void deleteBookFromBasket(int userId, int productId) throws Exception {
        var user = userRepository.findById(userId);
        var product = productRepository.findById(productId);

        var existBaskets = basketRepository.findByUserId(userId);

        for (var basket : existBaskets) {
            if (basket.getProduct().getId() == productId) {
                basketRepository.delete(basket);
                return;
            }
        }
    }
}
