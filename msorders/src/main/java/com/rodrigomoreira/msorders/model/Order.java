package com.rodrigomoreira.msorders.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Integer quantity;

    public Order(String productName, Integer quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

}
