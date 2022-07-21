package com.lslm.ordersapi.consumers;

import com.google.gson.Gson;
import com.lslm.ordersapi.clients.StockClient;
import com.lslm.ordersapi.entities.Order;
import com.lslm.ordersapi.entities.ProductStock;
import com.lslm.ordersapi.producers.OrderProducer;
import com.lslm.ordersapi.services.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderConsumer {
    @Autowired
    private OrderService orderService;

    @Autowired
    private StockClient stockClient;

    @Autowired
    private OrderProducer orderProducer;

    @RabbitListener(queues = {"CREATE-ORDER"})
    public void consumeCreateOrder(@Payload String body) throws IOException {
        Gson gson = new Gson();
        Order order = gson.fromJson(body, Order.class);

        ProductStock productStock = stockClient.getProductStock(order.getProductId());
        Order orderCreated = orderService.create(order, productStock);

        if (orderCreated != null)
            orderProducer.produceCreatedOrder(orderCreated);
    }
}
