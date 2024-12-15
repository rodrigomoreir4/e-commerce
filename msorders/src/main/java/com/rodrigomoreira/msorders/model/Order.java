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
    private String name;
    private Integer quantity;

    public Order(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

}
