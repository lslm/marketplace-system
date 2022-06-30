package com.lslm.marketplacebff.controllers;

import com.lslm.marketplacebff.adapters.requests.ProductCreationRequest;
import com.lslm.marketplacebff.adapters.responses.ProductCreationResponse;
import com.lslm.marketplacebff.clients.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    ProductClient productClient;

    @PostMapping
    public ResponseEntity<ProductCreationResponse> create(@RequestBody ProductCreationRequest newProduct) throws IOException {
        ProductCreationResponse product = productClient.createProduct(newProduct);

        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.CREATED);

        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping
    public ResponseEntity<List<ProductCreationResponse>> listAll() throws IOException {
        List<ProductCreationResponse> products = productClient.listProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
