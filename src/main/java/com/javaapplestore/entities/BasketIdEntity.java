package com.javaapplestore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BasketIdEntity implements Serializable {
    @Column(name = "product_id")
    private int productId;

    @Column(name = "user_id")
    private int userId;
}
