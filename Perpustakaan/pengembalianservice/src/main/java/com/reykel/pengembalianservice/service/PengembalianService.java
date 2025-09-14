package com.reykel.pengembalianservice.service;

import com.reykel.pengembalianservice.model.Pengembalian;
import com.reykel.pengembalianservice.repository.PengembalianRepository;
import com.reykel.pengembalianservice.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PengembalianService {

    @Autowired
    private PengembalianRepository repo;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    // ===== CRUD Lokal =====
    public List<Pengembalian> getAll() {
        return repo.findAll();
    }

    public Pengembalian save(Pengembalian pengembalian) {
        return repo.save(pengembalian);
    }

    public Optional<Pengembalian> getById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ===== Integrasi ke microservices lain =====
    public ResponseTemplate getPengembalianWithDetail(Long id) {
        Pengembalian pengembalian = repo.findById(id).orElse(null);
        if (pengembalian == null) return null;

        // 🔹 Cari instance PEMINJAMAN-SERVICE
        ServiceInstance peminjamanInstance = discoveryClient.getInstances("PEMINJAMAN-SERVICE").get(0);
        Peminjaman peminjaman = restTemplate.getForObject(
                peminjamanInstance.getUri() + "/api/peminjaman/" + pengembalian.getPeminjamanId(),
                Peminjaman.class
        );

        // 🔹 Cari instance ANGGOTA-SERVICE
        ServiceInstance anggotaInstance = discoveryClient.getInstances("ANGGOTA-SERVICE").get(0);
        Anggota anggota = restTemplate.getForObject(
                anggotaInstance.getUri() + "/api/anggota/" + peminjaman.getAnggotaId(),
                Anggota.class
        );

        // 🔹 Cari instance BUKU-SERVICE
        ServiceInstance bukuInstance = discoveryClient.getInstances("BUKU-SERVICE").get(0);
        Buku buku = restTemplate.getForObject(
                bukuInstance.getUri() + "/api/buku/" + peminjaman.getBukuId(),
                Buku.class
        );

        // Gabungkan hasil
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPengembalian(pengembalian);
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);

        return vo;
    }
}
