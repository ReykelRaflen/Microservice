package com.reykel.peminjamanqueryservice.peminjamanqueryservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.reykel.peminjamanqueryservice.peminjamanqueryservice.dto.PeminjamanEventDTO;
import com.reykel.peminjamanqueryservice.peminjamanqueryservice.model.PeminjamanView;
import com.reykel.peminjamanqueryservice.peminjamanqueryservice.repository.PeminjamanViewRepository;

@Service
public class PeminjamanEventListener {

    private final PeminjamanViewRepository repository;

    public PeminjamanEventListener(PeminjamanViewRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void handlePeminjamanCreatedEvent(PeminjamanEventDTO event) {
        System.out.println("Menerima event peminjaman: " + event.getPeminjamanId());

        PeminjamanView view = new PeminjamanView();
        view.setPeminjamanId(event.getPeminjamanId());
        view.setNamaAnggota(event.getAnggotaNama());
        view.setEmailAnggota(event.getAnggotaEmail());
        view.setJudulBuku(event.getBukuJudul());
        view.setTanggalPinjam(event.getTanggalPinjam());
        view.setTanggalKembali(event.getTanggalKembali());

        repository.save(view);
        System.out.println("View model untuk peminjaman " + event.getPeminjamanId() + " berhasil disimpan ke MongoDB.");
    }
}