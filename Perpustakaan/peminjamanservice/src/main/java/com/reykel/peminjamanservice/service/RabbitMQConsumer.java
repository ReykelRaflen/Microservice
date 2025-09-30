package com.reykel.peminjamanservice.service;

import com.reykel.peminjamanservice.model.Peminjaman;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = "peminjamanQueue")
    public void consumeMessage(Peminjaman peminjaman) {
        System.out.println("📥 Event Peminjaman diterima dari RabbitMQ: " + peminjaman);

        // Bisa ditambahkan logika lain di sini:
        // - kirim email notifikasi
        // - update status peminjaman
        // - trigger ke service lain
    }
}
