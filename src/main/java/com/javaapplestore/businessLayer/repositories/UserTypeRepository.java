package com.javaapplestore.businessLayer.repositories;

import com.javaapplestore.entities.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Integer> {
    Optional<UserTypeEntity> findByType(String type);
}

