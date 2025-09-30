package com.reykel.peminjamanservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reykel.peminjamanservice.config.RabbitMQConfig;
import com.reykel.peminjamanservice.model.Peminjaman;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendPeminjamanEvent(Peminjaman peminjaman) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                peminjaman
        );
        System.out.println("📤 Event Peminjaman dikirim ke RabbitMQ: " + peminjaman);
    }
}
