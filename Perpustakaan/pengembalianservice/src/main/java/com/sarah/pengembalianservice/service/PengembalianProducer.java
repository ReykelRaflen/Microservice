package com.sarah.pengembalianservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sarah.pengembalianservice.model.Pengembalian;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class PengembalianProducer {

    private static final String QUEUE = "pengembalian_queue";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendToQueue(Pengembalian pengembalian) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setPort(5672);

            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {

                channel.queueDeclare(QUEUE, true, false, false, null);
                String message = objectMapper.writeValueAsString(pengembalian);

                channel.basicPublish("", QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println("📤 Sent to RabbitMQ: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
