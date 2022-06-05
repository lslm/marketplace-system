package com.lslm.customersapi.controllers;

import com.lslm.customersapi.entities.Customer;
import com.lslm.customersapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer newCustomer) {
        Customer customer = customerService.create(newCustomer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
