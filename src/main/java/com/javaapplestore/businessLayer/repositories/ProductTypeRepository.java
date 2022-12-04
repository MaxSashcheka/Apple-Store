package com.javaapplestore.businessLayer.repositories;

import com.javaapplestore.entities.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Integer> {

}
