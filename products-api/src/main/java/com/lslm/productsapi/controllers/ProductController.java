package com.lslm.productsapi.controllers;

import com.lslm.productsapi.adapters.requests.ProductCreationRequest;
import com.lslm.productsapi.entities.Product;
import com.lslm.productsapi.producers.ProductProducer;
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

    @Autowired
    private ProductProducer productProducer;

    @PostMapping()
    public ResponseEntity create(@RequestBody ProductCreationRequest productCreationRequest) {
        productProducer.publishCreateProduct(productCreationRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
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
