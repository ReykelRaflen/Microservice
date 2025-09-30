package com.reykel.peminjamanservice.service;

import com.reykel.peminjamanservice.model.Peminjaman;
import com.reykel.peminjamanservice.repository.PeminjamanRepository;
import com.reykel.peminjamanservice.vo.Anggota;
import com.reykel.peminjamanservice.vo.Buku;
import com.reykel.peminjamanservice.vo.ResponseTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PeminjamanService {

    @Autowired
    private PeminjamanRepository repo;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // === Konstanta RabbitMQ ===
    private static final String EXCHANGE = "peminjamanExchange";
    private static final String ROUTING_KEY = "peminjamanRoutingKey";

    // ===== CRUD Lokal =====
    public List<Peminjaman> getAll() {
        return repo.findAll();
    }

    public Peminjaman save(Peminjaman peminjaman) {
        Peminjaman saved = repo.save(peminjaman);

        // === Publish ke RabbitMQ ===
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, saved);
        System.out.println("📤 Event Peminjaman dikirim ke RabbitMQ: " + saved);

        return saved;
    }

    public Optional<Peminjaman> getById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ===== Integrasi dengan Microservices lain =====
    public ResponseTemplate getPeminjamanWithDetail(Long id) {
        Optional<Peminjaman> opt = repo.findById(id);
        if (opt.isEmpty()) {
            return null; // atau throw exception
        }

        Peminjaman peminjaman = opt.get();

        // === Cari instance Anggota-Service ===
        ServiceInstance anggotaInstance = discoveryClient.getInstances("ANGGOTA-SERVICE").get(0);
        Anggota anggota = restTemplate.getForObject(
                anggotaInstance.getUri() + "/api/anggota/" + peminjaman.getAnggotaId(),
                Anggota.class
        );

        // === Cari instance Buku-Service ===
        ServiceInstance bukuInstance = discoveryClient.getInstances("BUKU-SERVICE").get(0);
        Buku buku = restTemplate.getForObject(
                bukuInstance.getUri() + "/api/buku/" + peminjaman.getBukuId(),
                Buku.class
        );

        // Gabungkan hasil
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);

        return vo;
    }
}
