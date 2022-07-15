package com.lslm.productsapi.producers;

import com.lslm.productsapi.adapters.ProductAdapter;
import com.lslm.productsapi.adapters.requests.ProductCreationRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProducer {

    @Autowired
    private final AmqpTemplate amqpTemplate;

    @Autowired
    private ProductAdapter productAdapter;

    public ProductProducer(AmqpTemplate template) {
        this.amqpTemplate = template;
    }

    public void publishCreateProduct(ProductCreationRequest productCreationRequest) {
        String payload = productAdapter.toJson(productCreationRequest);
        amqpTemplate.convertAndSend("CREATE-PRODUCT", payload);
    }
}
