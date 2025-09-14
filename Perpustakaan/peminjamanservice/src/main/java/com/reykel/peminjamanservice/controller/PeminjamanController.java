package com.reykel.peminjamanservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reykel.peminjamanservice.model.Peminjaman;
import com.reykel.peminjamanservice.service.PeminjamanService;
import com.reykel.peminjamanservice.vo.ResponseTemplate;

@RestController
@RequestMapping("api/peminjaman")
public class PeminjamanController {

    @Autowired
    private PeminjamanService service;

    // 🔹 Ambil semua peminjaman
    @GetMapping
    public List<Peminjaman> getAll() {
        return service.getAll();
    }

    // 🔹 Simpan peminjaman baru
    @PostMapping
    public Peminjaman save(@RequestBody Peminjaman peminjaman) {
        return service.save(peminjaman);
    }

    // 🔹 Ambil peminjaman by ID (hanya entity Peminjaman)
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
