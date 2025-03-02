package com.Lins.product_manager_study.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name="products")
@Entity(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String category;

    private Integer price_in_cents;


    public Product(RequestProduct requestProduct) {
        this.id = requestProduct.id();
        this.name = requestProduct.name();
        this.category = requestProduct.category();
        this.price_in_cents = (Integer) requestProduct.price_in_cents();
    }
}
