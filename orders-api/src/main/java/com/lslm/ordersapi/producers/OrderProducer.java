package com.lslm.ordersapi.producers;

import com.google.gson.Gson;
import com.lslm.ordersapi.entities.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {
    @Autowired
    private final AmqpTemplate amqpTemplate;

    Gson gson = new Gson();

    public OrderProducer(AmqpTemplate template) {
        amqpTemplate = template;
    }

    public void produceCreateOrder(Order order) {
        String payload = gson.toJson(order);
        amqpTemplate.convertAndSend("CREATE-ORDER", payload);
    }

    public void produceOrderCreated(Order order) {
        String payload = gson.toJson(order);
        amqpTemplate.convertAndSend("order-exchange", "order-routing-key", payload);
    }
}
