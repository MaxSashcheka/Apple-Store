package com.javaapplestore.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaapplestore.businessLayer.models.product.GetProduct;
import com.javaapplestore.businessLayer.models.user.GetUser;
import com.javaapplestore.businessLayer.repositories.UserRepository;
import com.javaapplestore.businessLayer.services.interfaces.ProductService;
import com.javaapplestore.businessLayer.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.ResponseEntity.internalServerError;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<GetUser> getProducts(@RequestParam String login) {
        try {
            var user = userService.getUserByLogin(login);
            if (user.isEmpty()) {
                return internalServerError().build();
            }
            return ResponseEntity.ok(user.get());
        } catch (Exception exception) {
            return internalServerError().build();
        }
    }

}
