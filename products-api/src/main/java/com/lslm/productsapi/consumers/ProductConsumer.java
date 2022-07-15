package com.lslm.productsapi.consumers;

import com.lslm.productsapi.adapters.ProductAdapter;
import com.lslm.productsapi.entities.Product;
import com.lslm.productsapi.services.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Autowired
    private ProductAdapter productAdapter;

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = {"CREATE-PRODUCT"})
    public void createProduct(@Payload String body) {
        Product product = productAdapter.fromJson(body);
        productService.create(product);
    }
}
