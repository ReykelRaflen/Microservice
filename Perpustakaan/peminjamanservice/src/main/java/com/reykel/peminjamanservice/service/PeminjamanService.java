package com.reykel.peminjamanservice.service;

import com.reykel.peminjamanservice.model.Peminjaman;
import com.reykel.peminjamanservice.repository.PeminjamanRepository;
import com.reykel.peminjamanservice.repository.PeminjamanReadRepository;
import com.reykel.peminjamanservice.vo.Anggota;
import com.reykel.peminjamanservice.vo.Buku;
import com.reykel.peminjamanservice.vo.ResponseTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;
import java.util.Optional;

@Service
public class PeminjamanService {

    @Autowired
    private PeminjamanRepository repo; // H2 (Command)

    @Autowired
    private PeminjamanReadRepository readRepo; // MongoDB (Query)

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    private static final String EXCHANGE = "peminjamanExchange";
    private static final String ROUTING_KEY = "peminjamanRoutingKey";

    // ===== CRUD =====
    public List<Peminjaman> getAll() {
        return repo.findAll();
    }

    public Peminjaman save(Peminjaman peminjaman) {
        // Simpan di H2 (Command)
        Peminjaman saved = repo.save(peminjaman);

        // Publish event ke RabbitMQ
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, saved);
        System.out.println("📤 Event Peminjaman dikirim ke RabbitMQ: " + saved);

        // Simpan di MongoDB (Query)
        readRepo.save(saved);

        return saved;
    }

    public Optional<Peminjaman> getById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
        readRepo.deleteById(id);
    }

    // ===== Detail Peminjaman (mengambil data Anggota + Buku) =====
    public ResponseTemplate getPeminjamanWithDetail(Long id) {
        Optional<Peminjaman> opt = repo.findById(id);
        if (opt.isEmpty()) return null;

        Peminjaman peminjaman = opt.get();

        // 🔹 Ambil data anggota
        ServiceInstance anggotaInstance = discoveryClient.getInstances("ANGGOTA-SERVICE").get(0);
        Anggota anggota = restTemplate.getForObject(
                anggotaInstance.getUri() + "/api/anggota/" + peminjaman.getAnggotaId(),
                Anggota.class
        );

        // 🔹 Ambil data buku
        ServiceInstance bukuInstance = discoveryClient.getInstances("BUKU-SERVICE").get(0);
        Buku buku = restTemplate.getForObject(
                bukuInstance.getUri() + "/api/buku/" + peminjaman.getBukuId(),
                Buku.class
        );

        // 🔹 Gabungkan ke ResponseTemplate
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);

        return vo;
    }
}
