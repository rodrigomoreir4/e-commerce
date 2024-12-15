package com.rodrigomoreira.msorders.controllers;

import com.rodrigomoreira.msorders.model.Order;
import com.rodrigomoreira.msorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        order.setName(order.getName().toLowerCase());
        kafkaTemplate.send("estoque-topico", order);
        Order newOrder = orderService.save(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updateOrder) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()){
            Order existingOrder = order.get();
            existingOrder.setName(updateOrder.getName());
            existingOrder.setQuantity(updateOrder.getQuantity());
            return ResponseEntity.ok(orderService.save(existingOrder));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderbyId(@PathVariable Long id){
        if (orderService.findById(id).isPresent()) {
            orderService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
