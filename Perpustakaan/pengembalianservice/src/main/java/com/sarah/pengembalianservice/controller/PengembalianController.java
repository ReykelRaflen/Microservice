package com.sarah.pengembalianservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sarah.pengembalianservice.model.Pengembalian;
import com.sarah.pengembalianservice.service.PengembalianService;
import com.sarah.pengembalianservice.vo.ResponseTemplate;

@RestController
@RequestMapping("/api/pengembalian")
@CrossOrigin(origins = "*") // biar bisa diakses dari frontend (Vue/React/Angular dsb)
public class PengembalianController {

    @Autowired
    private PengembalianService service;

    // 🔹 Get all pengembalian
    @GetMapping
    public ResponseEntity<List<Pengembalian>> getAll() {
        List<Pengembalian> list = service.getAll();
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

    // 🔹 Save pengembalian baru (POST) -> langsung hitung denda
    @PostMapping
    public ResponseEntity<Pengembalian> save(@Valid @RequestBody Pengembalian pengembalian) {
        Pengembalian saved = service.simpanPengembalian(pengembalian); // ✅ pakai logika hitung denda
        return ResponseEntity
                .created(URI.create("/api/pengembalian/" + saved.getId()))
                .body(saved);
    }

    // 🔹 Get pengembalian by ID
   @GetMapping("/{id}")
public ResponseEntity<Pengembalian> getById(@PathVariable Long id) {
    Pengembalian pengembalian = service.getById(id);
    return pengembalian != null
            ? ResponseEntity.ok(pengembalian)
            : ResponseEntity.notFound().build();
}


    // 🔹 Delete pengembalian
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Get pengembalian detail (dengan data peminjaman, anggota, buku)
    @GetMapping("/{id}/detail")
    public ResponseEntity<ResponseTemplate> getPengembalianWithDetail(@PathVariable Long id) {
        ResponseTemplate response = service.getPengembalianWithDetail(id);
        return response == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(response);
    }
}
