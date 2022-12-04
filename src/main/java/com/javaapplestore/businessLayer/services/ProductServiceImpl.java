package com.javaapplestore.businessLayer.services;

import com.javaapplestore.businessLayer.models.product.*;
import com.javaapplestore.businessLayer.repositories.ProductRepository;
import com.javaapplestore.businessLayer.repositories.ProductTypeRepository;
import com.javaapplestore.businessLayer.services.interfaces.ProductService;
import com.javaapplestore.entities.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    public List<GetProduct> getAllProducts() {
        var products = productRepository.findAll();
        var getProductsList = new ArrayList<GetProduct>();

        for (ProductEntity product : products) {
            var newProduct = new GetProduct();
            newProduct.setId(product.getId());
            newProduct.setName(product.getName());
            newProduct.setPrice(product.getPrice());

            var productType = new ProductTypeForGetProduct();
            var productTypeExist = productTypeRepository.findById(
                product.getProductType().getId()
            );
            if (productTypeExist.isEmpty()) {
                throw new IllegalArgumentException();
            }
            productType.setId(productTypeExist.get().id);
            productType.setType(productTypeExist.get().name);

            newProduct.setProductType(productType);
            getProductsList.add(newProduct);
        }
        return getProductsList;
    }

    public GetProduct getProduct(int id) throws Exception {
        for (var product: getAllProducts()) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new Exception();
    }

    public void createProduct(PostProduct postProduct) {
        var productEntity = new ProductEntity();
        productEntity.setName(postProduct.getName());
        productEntity.setPrice(postProduct.getPrice());

        var productTypeExist = productTypeRepository.findById(
            postProduct.getProductTypeId()
        );
        if (productTypeExist.isEmpty()) {
            throw new IllegalArgumentException();
        }
        productEntity.setProductType(productTypeExist.get());

        productRepository.save(productEntity);
    }

    public void updateProduct(PutProduct putProduct) {
        var productEntity = productRepository.findById(putProduct.getId());
        if (productEntity.isEmpty()) {
            throw new IllegalArgumentException();
        }

        productEntity.get().setId(putProduct.getId());
        productEntity.get().setName(putProduct.getName());
        productEntity.get().setPrice(putProduct.getPrice());

        var productTypeExist = productTypeRepository.findById(
            putProduct.getProductTypeId()
        );
        if (productTypeExist.isEmpty()) {
            throw new IllegalArgumentException();
        }
        productEntity.get().setProductType(productTypeExist.get());

        productRepository.save(productEntity.get());
    }

    public void deleteProduct(DeleteProduct deleteProductModel) {
        var product = productRepository.findById(deleteProductModel.getId());
        if (!product.isEmpty()) {
            productRepository.delete(product.get());
        }
    }
}
