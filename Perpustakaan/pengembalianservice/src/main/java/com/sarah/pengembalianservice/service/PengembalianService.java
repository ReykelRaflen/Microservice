package com.sarah.pengembalianservice.service;

import com.sarah.pengembalianservice.model.Pengembalian;
import com.sarah.pengembalianservice.repository.PengembalianRepository;
import com.sarah.pengembalianservice.vo.Peminjaman;
import com.sarah.pengembalianservice.vo.ResponseTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PengembalianService {

    @Autowired
    private PengembalianRepository pengembalianRepository;

    @Autowired
    private RestTemplate restTemplate;

    // ✅ Simpan + hitung denda
    public Pengembalian simpanPengembalian(Pengembalian pengembalian) {
        Pengembalian saved = pengembalianRepository.save(pengembalian);

        String url = "http://localhost:9010/api/peminjaman/" + pengembalian.getPeminjamanId();
        Peminjaman peminjaman = restTemplate.getForObject(url, Peminjaman.class);

        if (peminjaman != null && peminjaman.getTanggalKembali() != null) {
            long terlambat = ChronoUnit.DAYS.between(
                    peminjaman.getTanggalKembali(),
                    pengembalian.getTanggalDikembalikan());

            if (terlambat > 0) {
                saved.setTerlambat((int) terlambat);
                saved.setDenda(terlambat * 2000);
            } else {
                saved.setTerlambat(0);
                saved.setDenda(0);
            }

            pengembalianRepository.save(saved);
        }

        return saved;
    }

    // ✅ Get all
    public List<Pengembalian> getAll() {
        return pengembalianRepository.findAll();
    }

    // ✅ Get by ID
    public Pengembalian getById(Long id) {
        return pengembalianRepository.findById(id).orElse(null);
    }

    // ✅ Delete
    public void delete(Long id) {
        pengembalianRepository.deleteById(id);
    }

    // ✅ Detail dengan join Peminjaman (bisa kamu lengkapi lagi dengan Anggota &
    // Buku)
    public ResponseTemplate getPengembalianWithDetail(Long id) {
        Optional<Pengembalian> pengembalianOpt = pengembalianRepository.findById(id);
        if (pengembalianOpt.isEmpty()) {
            return null;
        }

        Pengembalian pengembalian = pengembalianOpt.get();

        String url = "http://localhost:9010/api/peminjaman/" + pengembalian.getPeminjamanId();
        Peminjaman peminjaman = restTemplate.getForObject(url, Peminjaman.class);

        ResponseTemplate response = new ResponseTemplate();
        response.setPengembalian(pengembalian);
        response.setPeminjaman(peminjaman);

        return response;
    }
}
