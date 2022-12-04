package com.javaapplestore.businessLayer.repositories;

import com.javaapplestore.entities.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
    List<BasketEntity> findByUserId(int userId);
}
