package com.lslm.productsapi.adapters;

import com.google.gson.Gson;
import com.lslm.productsapi.adapters.requests.ProductCreationRequest;
import com.lslm.productsapi.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductAdapter {
    public String toJson(ProductCreationRequest productCreationRequest) {
        Gson gson = new Gson();
        return gson.toJson(productCreationRequest);
    }

    public Product fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Product.class);
    }
}
