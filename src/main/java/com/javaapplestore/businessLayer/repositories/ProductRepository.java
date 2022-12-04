package com.javaapplestore.businessLayer.repositories;

import com.javaapplestore.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
