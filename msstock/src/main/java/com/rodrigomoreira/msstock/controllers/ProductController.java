package com.rodrigomoreira.msstock.controllers;

import com.rodrigomoreira.msstock.model.Product;
import com.rodrigomoreira.msstock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @GetMapping("/find")
    public  ResponseEntity<Product> getProduct(@RequestParam String name) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        product.setName(product.getName().toLowerCase());
        productRepository.save(product);
        return "Product added to stock!";
    }

    @PutMapping
    public String updateProductQuantity(@RequestParam String name, @RequestParam int quantity) {
        Product product = productRepository.findByName(name.toLowerCase());
        if (product != null){
            product.setQuantity(quantity);
            productRepository.save(product);
            return "Product quantity updated!";
        } else {
            return "Product not found or insufficient quantity!";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "Product removed from stock!";
    }

}
