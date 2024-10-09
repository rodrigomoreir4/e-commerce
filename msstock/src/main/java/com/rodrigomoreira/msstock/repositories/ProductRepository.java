package com.rodrigomoreira.msstock.repositories;

import com.rodrigomoreira.msstock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
