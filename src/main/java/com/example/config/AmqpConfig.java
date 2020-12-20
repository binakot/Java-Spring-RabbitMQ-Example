package com.example.config;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Base64UrlNamingStrategy;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("example.exchange");
    }

    @Bean
    public Queue marketingQueue() {
        return new AnonymousQueue(new Base64UrlNamingStrategy("example.queue.marketing."));
    }

    @Bean
    public Queue financeQueue() {
        return new AnonymousQueue(new Base64UrlNamingStrategy("example.queue.finance."));
    }

    @Bean
    public Queue adminQueue() {
        return new AnonymousQueue(new Base64UrlNamingStrategy("example.queue.admin."));
    }

    @Bean
    public Binding marketingBinding(final Queue marketingQueue, final DirectExchange directExchange) {
        return BindingBuilder.bind(marketingQueue).to(directExchange).with("example.route.marketing");
    }

    @Bean
    public Binding financeBinding(final Queue financeQueue, final DirectExchange directExchange) {
        return BindingBuilder.bind(financeQueue).to(directExchange).with("example.route.finance");
    }

    @Bean
    public Binding adminBinding(final Queue adminQueue, final DirectExchange directExchange) {
        return BindingBuilder.bind(adminQueue).to(directExchange).with("example.route.admin");
    }
}
