package com.javaapplestore.entities;

import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Service
@Getter
@Entity
@Table(name = "product_type")
public class ProductTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    @Column(name = "name")
    public String name;
}
