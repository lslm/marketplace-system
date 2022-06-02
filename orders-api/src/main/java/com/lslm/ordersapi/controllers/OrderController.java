package com.lslm.ordersapi.controllers;

import com.lslm.ordersapi.entities.Order;
import com.lslm.ordersapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order newOrder) {
        Order order = orderService.create(newOrder);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
