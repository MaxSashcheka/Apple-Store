package com.javaapplestore.web.controllers;

import com.javaapplestore.businessLayer.models.product.*;
import com.javaapplestore.businessLayer.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<GetProduct>> getProducts() {
        try {
            var products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception exception) {
            return ResponseEntity.status(500).build();
        }
    }

    @RequestMapping("/compose-product")
    public String getComposeProductPage() {
        return "compose-product";
    }

    @RequestMapping("/product")
    public String getProducts(Model model, @AuthenticationPrincipal User user) {
        try{
            var products = productService.getAllProducts();
            model.addAttribute("products", products);
            model.addAttribute("springUser", user);
            return "product";

        }catch (Exception ex)
        {
            return "error";
        }
    }

    @PostMapping("/compose-product")
    public ResponseEntity createNewProduct(@RequestBody PostProduct postProductModel) {
        try {
            productService.createProduct(postProductModel);
            return ResponseEntity.status(200).build();
        } catch (Exception ex){
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/product")
    public ResponseEntity deleteProduct(@RequestBody DeleteProduct deleteProductModel) {
        try {
            productService.deleteProduct(deleteProductModel);
            return ResponseEntity.status(200).build();
        } catch (Exception ex){
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity postProduct(@RequestBody PostProduct postProduct) {
        try {
            productService.createProduct(postProduct);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception exception) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping
    public ResponseEntity postProduct(@RequestBody PutProduct putProduct) {
        try {
            productService.updateProduct(putProduct);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception exception) {
            return ResponseEntity.status(500).build();
        }
    }
}
