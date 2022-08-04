package com.lslm.stockapi.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DecreaseStockConsumer {

    @RabbitListener(queues = {"decrease-stock"})
    public void consumeDecreaseStock(@Payload String body) throws IOException {
        System.out.println(body);
    }

}
