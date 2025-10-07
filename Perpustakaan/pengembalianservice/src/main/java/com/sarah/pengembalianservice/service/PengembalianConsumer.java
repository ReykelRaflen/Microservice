package com.sarah.pengembalianservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import com.sarah.pengembalianservice.model.Pengembalian;
import com.sarah.pengembalianservice.repository.PengembalianReadRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
public class PengembalianConsumer {

    private static final String QUEUE = "pengembalian_queue";
    private final PengembalianReadRepository readRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PengembalianConsumer(PengembalianReadRepository readRepo) {
        this.readRepo = readRepo;
    }

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
                Pengembalian pengembalian = objectMapper.readValue(message, Pengembalian.class);
                readRepo.save(pengembalian); // ⬅️ Simpan ke MongoDB
                System.out.println("✅ Saved to MongoDB: " + pengembalian.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        channel.basicConsume(QUEUE, true, deliverCallback, consumerTag -> {});
    }
}
