package com.javaapplestore.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "basket")
@NoArgsConstructor
public class BasketEntity {

    @EmbeddedId
    private BasketIdEntity id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "count")
    private int count;

    public BasketEntity(ProductEntity product, UserEntity user, int count) {
        this.id = new BasketIdEntity(product.getId(), user.getId());
        this.product = product;
        this.user = user;
        this.count = count;
    }
}

