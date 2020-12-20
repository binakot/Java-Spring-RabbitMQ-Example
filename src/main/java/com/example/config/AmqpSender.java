package com.example.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange directExchange;

    public void send(final String message, final String routingKey) {
        template.convertAndSend(directExchange.getName(), routingKey, message);
    }
}
