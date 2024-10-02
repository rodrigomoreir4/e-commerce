package com.rodrigomoreira.msorders.respositories;

import com.rodrigomoreira.msorders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
