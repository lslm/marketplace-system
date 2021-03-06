package com.lslm.customersapi.controllers;

import com.lslm.customersapi.entities.Address;
import com.lslm.customersapi.entities.Customer;
import com.lslm.customersapi.services.AddressService;
import com.lslm.customersapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer newCustomer) {
        Customer customer = customerService.create(newCustomer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping("/{customerId}/address")
    public ResponseEntity<Address> createAddress(@RequestBody Address newAddress, @PathVariable UUID customerId) {
        newAddress.setCustomerId(customerId);
        Address address = addressService.create(newAddress);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
}
