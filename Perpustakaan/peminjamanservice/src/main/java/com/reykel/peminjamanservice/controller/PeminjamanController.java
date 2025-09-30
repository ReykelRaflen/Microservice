package com.reykel.peminjamanservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reykel.peminjamanservice.model.Peminjaman;
import com.reykel.peminjamanservice.service.PeminjamanService;
import com.reykel.peminjamanservice.service.EmailService; // 🔔 import EmailService
import com.reykel.peminjamanservice.vo.ResponseTemplate;

@RestController
@RequestMapping("api/peminjaman")
public class PeminjamanController {

    @Autowired
    private PeminjamanService service;

    @Autowired
    private EmailService emailService; // 🔔 untuk notifikasi email

    // 🔹 Ambil semua peminjaman
    @GetMapping
    public List<Peminjaman> getAll() {
        return service.getAll();
    }

    // 🔹 Simpan peminjaman baru + kirim notifikasi email
   @PostMapping
public Peminjaman save(@RequestBody Peminjaman peminjaman) {
    Peminjaman saved = service.save(peminjaman);

    String subject = "Notifikasi Peminjaman Buku";
    String message = "Halo, " + saved.getNamaPeminjam() +
                     "\n\nAnda baru saja meminjam buku: " + saved.getNamaBuku() +
                     "\nTanggal Peminjaman: " + saved.getTanggalPinjam() +
                     "\n\nHarap dikembalikan tepat waktu. Terima kasih.";

    // 🔹 Gunakan email dari Peminjaman agar tidak null
    if (saved.getEmailPeminjam() != null && !saved.getEmailPeminjam().isBlank()) {
        emailService.sendEmail(saved.getEmailPeminjam(), subject, message);
    } else {
        System.err.println("❌ Email peminjam kosong, tidak kirim notifikasi.");
    }

    return saved;
}


    // 🔹 Ambil peminjaman by ID
    @GetMapping("/{id}")
    public ResponseEntity<Peminjaman> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Hapus peminjaman
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // 🔹 Ambil detail peminjaman (Peminjaman + Anggota + Buku)
    @GetMapping("/{id}/detail")
    public ResponseEntity<ResponseTemplate> getPeminjamanWithDetail(@PathVariable Long id) {
        ResponseTemplate response = service.getPeminjamanWithDetail(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
