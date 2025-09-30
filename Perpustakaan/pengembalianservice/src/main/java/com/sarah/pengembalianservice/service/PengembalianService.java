package com.sarah.pengembalianservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sarah.pengembalianservice.model.Pengembalian;
import com.sarah.pengembalianservice.repository.PengembalianRepository;
import com.sarah.pengembalianservice.vo.*;

import java.time.temporal.ChronoUnit;
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

    public Optional<Pengembalian> getById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ===== Simpan + hitung keterlambatan & denda =====
    public Pengembalian save(Pengembalian pengembalian) {
        // Ambil data peminjaman dari microservice
        ServiceInstance peminjamanInstance = discoveryClient.getInstances("PEMINJAMAN-SERVICE").get(0);
        Peminjaman peminjaman = restTemplate.getForObject(
                peminjamanInstance.getUri() + "/api/peminjaman/" + pengembalian.getPeminjamanId(),
                Peminjaman.class
        );

        // Hitung keterlambatan & denda
        if (pengembalian.getTanggalDikembalikan() != null && peminjaman.getTanggalKembali() != null) {
            long selisihHari = ChronoUnit.DAYS.between(
                    peminjaman.getTanggalKembali(),
                    pengembalian.getTanggalDikembalikan()
            );
            if (selisihHari > 0) {
                pengembalian.setTerlambat((int) selisihHari);
                pengembalian.setDenda(selisihHari * 2000L); // 2000 per hari
            } else {
                pengembalian.setTerlambat(0);
                pengembalian.setDenda(0L);
            }
        }

        return repo.save(pengembalian);
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
        vo.setDenda(pengembalian.getDenda() != null ? pengembalian.getDenda() : 0L);

        return vo;
    }
}
