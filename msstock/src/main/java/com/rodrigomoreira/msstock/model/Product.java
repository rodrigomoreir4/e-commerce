package com.rodrigomoreira.msstock.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;

    public Product(String name, Integer stock) {
        this.name = name;
        this.quantity = quantity;
    }

}
