package com.lslm.productsapi.controllers;

import com.lslm.productsapi.entities.Product;
import com.lslm.productsapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RolesAllowed("admin")
    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody Product newProduct) {
        Product product = productService.create(newProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> find(@PathVariable UUID id) {
        Product product = productService.find(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
