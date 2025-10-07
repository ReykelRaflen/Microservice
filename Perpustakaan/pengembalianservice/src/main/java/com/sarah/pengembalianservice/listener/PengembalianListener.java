package com.sarah.pengembalianservice.listener;

import com.sarah.pengembalianservice.model.Pengembalian;
import com.sarah.pengembalianservice.repository.PengembalianReadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PengembalianListener {

    @Autowired
    private PengembalianReadRepository readRepo;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "${rabbitmq.pengembalian.queue}")
    public void receiveMessage(String json) {
        try {
            Pengembalian pengembalian = objectMapper.readValue(json, Pengembalian.class);
            // Simpan/update di MongoDB
            readRepo.save(pengembalian);
            System.out.println("Pengembalian diterima dan disimpan di MongoDB: " + pengembalian.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
