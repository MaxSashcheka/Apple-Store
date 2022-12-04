package com.javaapplestore.businessLayer.services.interfaces;

import com.javaapplestore.businessLayer.models.product.DeleteProduct;
import com.javaapplestore.businessLayer.models.product.GetProduct;
import com.javaapplestore.businessLayer.models.product.PostProduct;
import com.javaapplestore.businessLayer.models.product.PutProduct;

import java.util.List;

public interface ProductService {
    List<GetProduct> getAllProducts();

    GetProduct getProduct(int id) throws Exception;
    void createProduct(PostProduct postProduct);
    void updateProduct(PutProduct putProduct);
    void deleteProduct(DeleteProduct deleteProductModel);

}
