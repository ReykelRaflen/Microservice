package com.reykel.bukuservice.service;

import com.reykel.bukuservice.config.RabbitConfig;
import com.reykel.bukuservice.model.BukuReadModel;
import com.reykel.bukuservice.model.Buku;
import com.reykel.bukuservice.repository.BukuRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BukuService {

    private final BukuRepository bukuRepository;
    private final RabbitTemplate rabbitTemplate;

    public BukuService(BukuRepository bukuRepository, RabbitTemplate rabbitTemplate) {
        this.bukuRepository = bukuRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Buku create(Buku buku) {
        Buku saved = bukuRepository.save(buku);

        // mapping ke read model
      BukuReadModel readModel = new BukuReadModel(
        saved.getId(),
        saved.getId(), // ini bisa dijadikan bukuId juga
        saved.getJudul(),
        saved.getPengarang(),
        saved.getPenerbit(),
        saved.getTahunTerbit()
);


        // kirim ke RabbitMQ
        rabbitTemplate.convertAndSend(RabbitConfig.BUKU_QUEUE, readModel);

        return saved;
    }
}
