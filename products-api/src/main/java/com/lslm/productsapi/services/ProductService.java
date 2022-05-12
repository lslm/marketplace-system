package com.lslm.productsapi.services;

import com.lslm.productsapi.entities.Product;
import com.lslm.productsapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product find(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
