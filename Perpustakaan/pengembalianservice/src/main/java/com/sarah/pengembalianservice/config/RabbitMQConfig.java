package com.sarah.pengembalianservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue pengembalianQueue() {
        return new Queue("pengembalianQueue", true); // true = durable
    }
}
