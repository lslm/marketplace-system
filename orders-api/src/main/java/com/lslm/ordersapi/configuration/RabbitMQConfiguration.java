package com.lslm.ordersapi.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    private String orderExchange = "order-exchange";
    private String orderCreatedQueue = "order-created";
    private String orderCreatedRoutingKey = "order-routing-key";

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(this.orderExchange);
    }

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(this.orderCreatedQueue);
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder
                .bind(orderCreatedQueue())
                .to(orderExchange())
                .with(this.orderCreatedRoutingKey);
    }

    public String getOrderExchange() {
        return orderExchange;
    }

    public String getOrderCreatedQueue() {
        return orderCreatedQueue;
    }

    public String getOrderCreatedRoutingKey() {
        return orderCreatedRoutingKey;
    }
}
