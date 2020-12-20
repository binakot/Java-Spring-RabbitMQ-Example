package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpReceiver.class);

    @RabbitListener(queues = "#{marketingQueue.name}")
    public void receiveMarketing(final String message) {
        LOGGER.info("Message from marketing: " + message);
    }

    @RabbitListener(queues = "#{financeQueue.name}")
    public void receiveFinance(final String message) {
        LOGGER.info("Message from finance: " + message);
    }

    @RabbitListener(queues = "#{adminQueue.name}")
    public void receiveAdmin(final String message) {
        LOGGER.info("Message from admin: " + message);
    }
}
