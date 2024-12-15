package com.rodrigomoreira.msstock.services;

import com.rodrigomoreira.msstock.model.Product;
import com.rodrigomoreira.msstock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private ProductRepository productRepository;

    @KafkaListener(topics = "estoque-topico", groupId = "estoque-group")
    public void isProductAvailable(Product product){
        Product storedProduct = productRepository.findByName(product.getName());
        if (storedProduct != null && product.getQuantity() <= storedProduct.getQuantity()){
            System.out.println("Venda de: " + product.getQuantity() + " " + product.getName() + " realizada com sucesso.");
            storedProduct.setQuantity(storedProduct.getQuantity() - product.getQuantity());
            productRepository.save(storedProduct);
        } else {
            System.out.println("Venda não realizada. Estoque insuficiente");
        }
    }
}
