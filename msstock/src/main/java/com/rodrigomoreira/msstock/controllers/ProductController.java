package com.rodrigomoreira.msstock.controllers;

import com.rodrigomoreira.msstock.model.Product;
import com.rodrigomoreira.msstock.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{name}")
    public  ResponseEntity<Product> getProductBynName(@PathVariable String name) {
        Product product = productService.getProductByName(name);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProductQuantity(@PathVariable String name, @RequestParam int quantity) {
        Product product = productService.getProductByName(name);
        try {
            Product updatedProduct = productService.updateProductQuantity(name, quantity);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

}
