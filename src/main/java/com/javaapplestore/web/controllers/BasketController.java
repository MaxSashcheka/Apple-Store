package com.javaapplestore.web.controllers;

import com.javaapplestore.businessLayer.models.basket.AddProductToBasket;
import com.javaapplestore.businessLayer.models.basket.DeleteBasket;
import com.javaapplestore.businessLayer.models.product.GetProduct;
import com.javaapplestore.businessLayer.models.product.PostProduct;
import com.javaapplestore.businessLayer.models.product.PutProduct;
import com.javaapplestore.businessLayer.services.interfaces.BasketService;
import com.javaapplestore.businessLayer.services.interfaces.ProductService;
import com.javaapplestore.businessLayer.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.internalServerError;

@Controller
public class BasketController {

    @Autowired
    UserService userService;

    @Autowired
    BasketService basketService;

    @RequestMapping("/basket")
    public String getBasket(Model model, @AuthenticationPrincipal User user) {
        try{
            var dbUser = userService.getUserByLogin(user.getUsername());
            if (dbUser.isEmpty()) {
                return "redirect:login";
            }
            var basket = basketService.getBasket(dbUser.get().getId());
            model.addAttribute("products", basket.getProducts());
//            model.addAttribute("products", products);
//            model.addAttribute("dbUser", dbUser.get());
//            model.addAttribute("springUser", user);
            return "basket";

        }catch (Exception ex)
        {
            var messages = "Упс!";
            model.addAttribute("messages", messages);
            return "error";
        }
    }

    @PostMapping("/basket")
    public ResponseEntity addToBasket(@RequestBody AddProductToBasket addProductToBasketModel,
                                      @AuthenticationPrincipal User user) {
        try {

            var dbUser = userService.getUserByLogin(user.getUsername());
            if (dbUser == null) {
                throw new Exception();
            }

            basketService.addProductToBasket(dbUser.get().getId(),
                                             addProductToBasketModel.getId());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception exception) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/basket")
    public ResponseEntity addToBasket(@RequestBody DeleteBasket deleteBasketModel,
                                      @AuthenticationPrincipal User user) {
        try {
            var dbUser = userService.getUserByLogin(user.getUsername());
            if (dbUser == null) {
                throw new Exception();
            }

            basketService.deleteBookFromBasket(dbUser.get().getId(), deleteBasketModel.getProductId());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception exception) {
            return ResponseEntity.status(500).build();
        }
    }


}
