package com.lslm.ordersapi.services;

import com.lslm.ordersapi.entities.Order;
import com.lslm.ordersapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order create(Order order) {
        return orderRepository.save(order);
    }
}