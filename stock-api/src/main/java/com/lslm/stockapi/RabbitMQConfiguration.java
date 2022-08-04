package com.lslm.stockapi;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Value("${rabbit.exchanges.order-exchange}")
    private String orderExchange;

    @Value("${rabbit.queues.decrease-stock}")
    private String decreaseStockQueue;

    @Value("${rabbit.routing-keys.order-routing-key}")
    private String orderRoutingKey;

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(this.orderExchange);
    }

    @Bean
    public Queue decreaseStockQueue() {
        return new Queue(this.decreaseStockQueue);
    }

    @Bean
    public Binding decreaseStockBinding() {
        return BindingBuilder
                .bind(decreaseStockQueue())
                .to(orderExchange())
                .with(this.orderRoutingKey);
    }

    public String getOrderExchange() {
        return orderExchange;
    }

    public String getDecreaseStockQueue() {
        return decreaseStockQueue;
    }

    public String getOrderRoutingKey() {
        return orderRoutingKey;
    }
}
