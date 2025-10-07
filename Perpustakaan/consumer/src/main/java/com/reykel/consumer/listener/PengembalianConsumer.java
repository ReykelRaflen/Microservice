package com.sarah.consumer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import com.sarah.consumer.model.Pengembalian;
import com.sarah.consumer.repository.PengembalianReadRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Component
public class PengembalianConsumer {

    private static final String QUEUE = "pengembalianQueue";

    @Autowired
    private PengembalianReadRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void startConsumer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE, true, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            try {
                // Convert JSON → Object
                Pengembalian pengembalian = objectMapper.readValue(message, Pengembalian.class);
                // Simpan ke MongoDB
                repository.save(pengembalian);
                System.out.println("✅ Saved to MongoDB: " + pengembalian.getPengembalianId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        channel.basicConsume(QUEUE, true, deliverCallback, consumerTag -> {});
    }
}
