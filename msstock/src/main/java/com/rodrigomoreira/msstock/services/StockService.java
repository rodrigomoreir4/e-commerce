package com.rodrigomoreira.msstock.services;

import com.rodrigomoreira.msstock.model.Product;
import com.rodrigomoreira.msstock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private ProductRepository productRepository;

    public boolean isProductAvailable(String productName, int quantity){
        Product product = productRepository.findByName(productName);
        return product != null && product.getStock() >= quantity;
    }

}
