package com.krukovska.client.repository;

import com.krukovska.client.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order getOrderById(long id);
}
